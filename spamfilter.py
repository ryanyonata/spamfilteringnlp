import csv

spam_data = []
nonspam_data = []

# Read data from csv
with open('data/spam.csv') as spam:
    reader = csv.reader(spam)
    for row in reader:
        spam_data.append(row[0])

with open('data/nonspam.csv') as spam:
    reader = csv.reader(spam)
    for row in reader:
        nonspam_data.append(row[0])
