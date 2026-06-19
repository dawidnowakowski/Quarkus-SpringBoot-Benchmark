import os
from generate_charts import plot_comprehensive_series, plot_wider_histogram

# Dynamically map paths
script_dir = os.path.dirname(os.path.abspath(__file__))
base_results_dir = os.path.abspath(os.path.join(script_dir, '../results'))

print(f"Scanning base directory: {base_results_dir}\n")

# Traverse the primary framework folders (e.g., QuarkusNative, SpringBoot_plain)
for framework in os.listdir(base_results_dir):
    framework_dir = os.path.join(base_results_dir, framework)
    
    if not os.path.isdir(framework_dir):
        continue

    # Path where CSV files currently reside
    csv_dir = os.path.join(framework_dir, 'results')
    if not os.path.exists(csv_dir):
        continue

    # Create the charts directory alongside the nested results directory
    charts_dir = os.path.join(framework_dir, 'charts')
    os.makedirs(charts_dir, exist_ok=True)
    
    print(f"[{framework}] Processing CSVs...")
    
    # Iterate over files to find Series
    for filename in os.listdir(csv_dir):
        if filename.endswith('.series.csv'):
            # Extract common prefix (e.g., 'measure-get-capacity.getStudent.135')
            prefix = filename.replace('.series.csv', '')
            hist_filename = f"{prefix}.histogram.csv"
            
            series_path = os.path.join(csv_dir, filename)
            hist_path = os.path.join(csv_dir, hist_filename)
            
            out_series_path = os.path.join(charts_dir, f"{prefix}_series.png")
            out_hist_path = os.path.join(charts_dir, f"{prefix}_histogram.png")
            
            # Generate Series Chart
            try:
                plot_comprehensive_series(series_path, out_series_path)
            except Exception as e:
                print(f"  [ERROR] Failed to plot series for {prefix}: {e}")
                
            # Generate Histogram Chart (if matching pair exists)
            if os.path.exists(hist_path):
                try:
                    plot_wider_histogram(hist_path, out_hist_path)
                except Exception as e:
                    print(f"  [ERROR] Failed to plot histogram for {prefix}: {e}")
            else:
                print(f"  [WARN] Missing histogram pair for {prefix}")

    print(f"[{framework}] Charts saved to {charts_dir}/\n")

print("All charting tasks completed successfully.")