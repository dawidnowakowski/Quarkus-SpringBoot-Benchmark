import pandas as pd
import matplotlib
import matplotlib.pyplot as plt
import os
import sys

matplotlib.use('Agg')

def plot_comprehensive_series(csv_path, output_name):
    df = pd.read_csv(csv_path)
    
    df['Time (s)'] = (df['Start'] - df['Start'].min()) / 1000.0

    for col in ['Mean', 'p50.0', 'p90.0', 'p99.0', 'p99.9', 'Max']:
        if col in df.columns:
            df[col] = df[col] / 1_000_000.0

    fig, ax1 = plt.subplots(figsize=(12, 6))
    
    color_tp = '#1f77b4'
    ax1.set_xlabel('Time (seconds)', fontsize=12)
    ax1.set_ylabel('Throughput (Requests/sec)', color=color_tp, fontsize=12)
    ax1.plot(df['Time (s)'], df['Responses'], color=color_tp, label='Throughput', linewidth=3, alpha=0.8)
    ax1.tick_params(axis='y', labelcolor=color_tp)
    ax1.grid(True, linestyle='--', alpha=0.6)

    ax2 = ax1.twinx()
    ax2.set_ylabel('Latency (ms)', color='black', fontsize=12)
    
    ax2.plot(df['Time (s)'], df['Mean'], color='#2ca02c', linestyle='-', label='Mean Latency', alpha=0.9, linewidth=1.5)
    ax2.plot(df['Time (s)'], df['p50.0'], color='#9467bd', linestyle='--', label='p50 Latency', alpha=0.9, linewidth=1.5)
    ax2.plot(df['Time (s)'], df['p90.0'], color='#d62728', linestyle='-.', label='p90 Latency', alpha=0.9, linewidth=1.5)
    ax2.plot(df['Time (s)'], df['p99.0'], color='#ff7f0e', linestyle=':', label='p99 Latency', linewidth=2)
    ax2.plot(df['Time (s)'], df['p99.9'], color='#8c564b', linestyle='-', label='p99.9 Latency', linewidth=2)

    lines_1, labels_1 = ax1.get_legend_handles_labels()
    lines_2, labels_2 = ax2.get_legend_handles_labels()
    ax1.legend(lines_1 + lines_2, labels_1 + labels_2, loc='upper center', bbox_to_anchor=(0.5, -0.15), ncol=3)

    plt.title('Throughput vs Comprehensive Latency Profile', fontsize=14, fontweight='bold')
    plt.tight_layout()
    plt.savefig(output_name, dpi=300, bbox_inches='tight')
    plt.close()

def plot_wider_histogram(csv_path, output_name):
    df = pd.read_csv(csv_path)
    
    df['Percentile_Pct'] = df['Percentile'] * 100

    plt.figure(figsize=(10, 6))
    plt.plot(df['Percentile_Pct'], df['Value'], color='#1f77b4', linewidth=2.5)
    plt.xlabel('Percentile (%)', fontsize=12)
    plt.ylabel('Latency (ms)', fontsize=12)
    plt.title('Latency Percentile Distribution (p50 to p100)', fontsize=14, fontweight='bold')
    
    plt.xlim(50, 100) 
    
    tail_data = df[df['Percentile_Pct'] >= 50]
    plt.ylim(0, tail_data['Value'].max() * 1.1) 
    
    percentiles_to_mark = [50, 90, 99, 99.9]
    colors = ['#9467bd', '#d62728', '#ff7f0e', '#8c564b']
    for p, c in zip(percentiles_to_mark, colors):
        val_at_p = df.iloc[(df['Percentile_Pct'] - p).abs().argsort()[:1]]['Value'].values[0]
        plt.axvline(x=p, color=c, linestyle='--', alpha=0.8, label=f'p{p} ({val_at_p:.2f}ms)')
    
    plt.legend(loc='upper left')
    plt.grid(True, linestyle='--', alpha=0.6)
    plt.tight_layout()
    plt.savefig(output_name, dpi=300)
    plt.close()

if __name__ == "__main__":
    print("This script is now a module. Run 'generate_all_charts.py' instead.")