import pandas as pd

filenames = ['quarkus_native.csv', 'quarkus.csv', 'spring_native_plain.csv', 'spring_native_tweaked.csv', 'spring_plain.csv', 'spring_tweaked.csv']
script_dir = os.path.dirname(os.path.abspath(__file__))
results_dir = os.path.abspath(os.path.join(script_dir, '../results/rss'))

pattern = re.compile(r"^\.csv$")
