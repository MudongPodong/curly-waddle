import cv2
import numpy as np
import matplotlib.pyplot as plt

img=cv2.imread('cutecat.jpg',cv2.IMREAD_GRAYSCALE)
resize_img=cv2.resize(img,(900,900))


cv2.imshow('Original',resize_img)

x=resize_img.copy()
x=x.astype('int8')
cv2.randn(x,0,0.3)
x=x.astype('uint8')
y=cv2.add(resize_img,x)

cv2.imshow('adapt noise screeen',y)

dst1=cv2.medianBlur(y,3)
cv2.imshow('median 3x3',dst1)

dst2=cv2.medianBlur(y,5)
cv2.imshow('median 5x5',dst2)

kernel=np.ones((3,3))/(3*3)
dst3=cv2.filter2D(y,-1,kernel)
cv2.imshow('mean 3x3',dst3)

kernel=np.ones((5,5))/(5*5)
dst4=cv2.filter2D(y,-1,kernel)
cv2.imshow('mean 5x5',dst4)
#cv2.waitKey(3000)

mask=np.array([[0,-1,0],[-1,4,-1],[0,-1,0]])
#dst5=cv2.GaussianBlur(y,(3,3),3)
dst6=cv2.Laplacian(resize_img,-1,mask)
cv2.imshow('Gaussian 3x3',dst6)

cv2.waitKey()