# import StemmerFactory class
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory

# create stemmer
factory = StemmerFactory()
stemmer = factory.create_stemmer()

import csv
resultFile = open("output.csv",'wb')
wr = csv.writer(resultFile, dialect='excel')

spam_data = []
spam_data2 = []
nonspam_data = []

# Read data from csv
with open('data/spam2.csv') as spam:
    reader = csv.reader(spam)
    for row in reader:
        spam_data.append(row[0])
        output='. '.join(row)
        output = stemmer.stem(output)
        output+=" SPAM"
        spam_data2 = []
        spam_data2.append(output)
	wr.writerow(spam_data2)


with open('data/nonspam2.csv') as spam:
    reader = csv.reader(spam)
    for row in reader:
        nonspam_data.append(row[0])
        output='. '.join(row)
        output = stemmer.stem(output)
        output+=" NONSPAM"
        spam_data2 = []
        spam_data2.append(output)
	wr.writerow(spam_data2)
