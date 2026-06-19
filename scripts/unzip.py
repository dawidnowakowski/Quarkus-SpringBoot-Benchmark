import os
import zipfile
import re

# Dynamically find the results directory relative to this script's location
script_dir = os.path.dirname(os.path.abspath(__file__))
results_dir = os.path.abspath(os.path.join(script_dir, '../results'))

# The strict regex pattern to match the required histogram and series files
pattern = re.compile(r"^measure-[a-zA-Z-]+\.[a-zA-Z]+\.\d+\.(?:histogram|series)\.csv$")

print(f"Targeting results directory: {results_dir}")

if not os.path.exists(results_dir):
    print("Error: The results directory does not exist at the resolved path.")
    exit(1)

for root, dirs, files in os.walk(results_dir):
    # os.walk will safely ignore the new 'results' subdirectories we create
    # because 'results.zip' will only be found in the parent directories.
    if 'results.zip' in files:
        zip_path = os.path.join(root, 'results.zip')
        
        # Define the path for the new nested directory
        extract_target_dir = os.path.join(root, 'results')
        
        print(f"\nProcessing {zip_path}...")
        
        # 1. Create the nested 'results' directory if it doesn't exist
        os.makedirs(extract_target_dir, exist_ok=True)
        
        # 2. Extract the zip file directly into the nested directory
        with zipfile.ZipFile(zip_path, 'r') as zip_ref:
            zip_ref.extractall(extract_target_dir)
            print(f"  Extracted to {extract_target_dir}/")
        
        # 3. Traverse the extracted files IN THE NESTED DIRECTORY and clean up
        for filename in os.listdir(extract_target_dir):
            file_path = os.path.join(extract_target_dir, filename)
            
            # Skip any nested directories the zip might have contained
            if os.path.isdir(file_path):
                continue
            
            # Filter strictly against the required pattern
            if pattern.match(filename):
                print(f"  [KEEP] {filename}")
            else:
                os.remove(file_path)