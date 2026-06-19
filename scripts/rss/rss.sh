#!/bin/bash

if [ -z "$1" ]; then
    echo "Usage: ./monitor_rss.sh <keyword> [output_file.csv]"
    echo "Example: ./monitor_rss.sh quarkus results/quarkus_rss.csv"
    exit 1
fi

KEYWORD=$1
OUT_FILE=${2:-"rss_log.csv"}

echo "Time_s,RSS,PID,Command" > "$OUT_FILE"

START_TIME=$(date +%s.%N)

echo "Monitoring processes matching: '$KEYWORD'"
echo "Logging data to: $OUT_FILE"
echo "Press [CTRL+C] to stop."
echo "------------------------------------------------"

while true; do
    RAW_OUT=$(ps -eo rss,pid,comm --sort -rss | grep "$KEYWORD" | head -n 1 | awk '{printf "%.1f %s %s\n", $1/1024, $2, $3}')
    if [ -n "$RAW_OUT" ]; then
        NOW=$(date +%s.%N)
        ELAPSED=$(awk "BEGIN {printf \"%.1f\", $NOW - $START_TIME}")

        CSV_DATA=$(echo "$RAW_OUT" | awk '{print $1","$2","$3}')

        echo "$ELAPSED,$CSV_DATA" >> "$OUT_FILE"

        echo "[$ELAPSED s] Logged: $CSV_DATA"
    fi

    sleep 0.5
done