import numpy as np
import pandas as pd

def cleanCSVdata(filename1, filename2):
    # features = np.genfromtxt(filename1, delimiter=',', names=True, case_sensitive=True, skiprows=1)
    features = pd.read_csv(filename1).to_numpy()
    output = pd.read_csv(filename2).to_numpy()
    # output = np.genfromtxt(filename2, delimiter=',', names=True, case_sensitive=True, skiprows=1)
    return features, output

# features, output = cleanCSVdata("/home/lvuser/data1.csv", "/home/lvuser/data2.csv")

from sklearn.linear_model import LinearRegression

reg = LinearRegression().fit(features, output)
coefs = reg.coef_
intercept = reg.intercept_

print(coefs)
print(intercept)