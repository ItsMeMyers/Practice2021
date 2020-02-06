# -*- coding: utf-8 -*-
"""
Created on Wed Feb  5 17:40:03 2020

@author: arnav
"""
import numpy as np

def cleanCSVdata(filename1, filename2):
    features = np.genfromtxt(filename1, delimiter=',', names=True, case_sensitive=True, skiprows=1)
    output = np.genfromtxt(filename2, delimiter=',', names=True, case_sensitive=True, skiprows=1)
    return features, output

features, output = cleanCSVdata("/home/lvuser/data1.csv", "/home/lvuser/data2.csv")

from sklearn.linear_model import LinearRegression

reg = LinearRegression().fit(features, output)
coefs = reg.coef_
intercept = reg.intercept_

print(coefs)
print(intercept)