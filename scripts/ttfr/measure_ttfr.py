import sys
import subprocess
import time
import urllib.request
import statistics
import os
import signal

def measure_ttfr(command, port=8080, endpoint="/students/1", runs=10):
    url = f"http://localhost:{port}{endpoint}"
    results = []

    print(f"Measuring TTFR for:\n> {command}\n")

    for i in range(1, runs + 1):
        print(f"Run {i}/{runs}...")

        start_time = time.perf_counter()

        process = subprocess.Popen(
            command,
            shell=True,
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
            preexec_fn=os.setsid
        )

        success = False
        ttfr_ms = 0

        for _ in range(6000):
            try:
                req = urllib.request.Request(url)
                with urllib.request.urlopen(req, timeout=0.05) as response:
                    if response.getcode() == 200:
                        end_time = time.perf_counter()
                        ttfr_ms = (end_time - start_time) * 1000
                        success = True
                        break
            except Exception:
                pass

            time.sleep(0.005)

        try:
            os.killpg(os.getpgid(process.pid), signal.SIGKILL)
            process.wait()
        except Exception as e:
            print(f"  [WARN] Cleanup issue: {e}")

        if success:
            print(f"  -> TTFR: {ttfr_ms:.2f} ms")
            results.append(ttfr_ms)
        else:
            print("  -> Timeout: App failed to respond with HTTP 200.")

        time.sleep(2)

    if results:
        mean = statistics.mean(results)
        stdev = statistics.stdev(results) if len(results) > 1 else 0

        print("\n" + "="*30)
        print("FINAL STATISTICAL RESULTS")
        print("="*30)
        print(f"Mean TTFR:           {mean:.2f} ms")
        print(f"Standard Deviation:  {stdev:.2f} ms")

        csv_file = "results/ttfr_master_results.csv"
        if not os.path.exists(csv_file):
            with open(csv_file, "w") as f:
                f.write("Command,Mean_TTFR_ms,StdDev_ms\n")

        with open(csv_file, "a") as f:
            f.write(f'"{command}",{mean:.2f},{stdev:.2f}\n')

        print(f"Results appended to {csv_file}")
    else:
        print("\nAll runs failed. Check the command or port.")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('Usage: python measure_ttfr.py "<startup_command>" [port]')
        print('Example: python measure_ttfr.py "taskset -c 4-11 java -jar my-app.jar" 8080')
        sys.exit(1)

    cmd = sys.argv[1]
    target_port = int(sys.argv[2]) if len(sys.argv) > 2 else 8080

    measure_ttfr(cmd, target_port, runs=10)