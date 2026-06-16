import pandas as pd
import os
import re

script_dir = os.path.dirname(os.path.abspath(__file__))
results_dir = os.path.abspath(os.path.join(script_dir, '../../results/rss'))

pattern = re.compile(r".*\.csv$")

print(f"Scanning directory: {results_dir}\n")

for root, dirs, files in os.walk(results_dir):
    print('test')
    for file in files:
        if pattern.match(file) and file != 'results.csv':
            file_path = os.path.join(root, file)
            
            try:
                df = pd.read_csv(file_path)
                
                rss_col = next((col for col in df.columns if 'RSS' in col.upper()), None)
                
                if rss_col:
                    mean_rss = df[rss_col].mean()
                    print(f"{file:<30} Mean {rss_col}: {mean_rss:.2f}")
                else:
                    print(f"{file:<30} [WARN] No RSS column found in this file.")
                    
            except Exception as e:
                print(f"{file:<30} [ERROR] Failed to process: {e}")