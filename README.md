-----------------------------
Math2605Project
Brian Wang
Wei Wang
Karl Nicodemus

-----------------------------
Hilbert (Part 1)
-----------------------------
-----------------------------
1. lu_fact

Input: name of .dat file (Matrix A)
Output: Matrix A, Matrix L, Matrix U, ||LU - A|| Error


Please place your .dat file in the root of the folder.
Your .dat files should contain a matrix with values seperated by a space.

To execute LU (lu_fact)
javac lu_fact.java
java lu_fact

The program should prompt for the name of your file. Enter the file name (including the .dat extention) and press enter.

e.g. To open a.dat, type "a.dat".

-----------------------------
2. HouseHolder and Givens LU


qr_fact_househ
Input: name of .dat file
Output: Matrix A, Matrix Q, Matrix R, ||QR - A|| Error

qr_fact_givens
Input: name of .dat file
Output: Matrix A, Matrix Q, Matrix R, ||QR - A|| Error

-Please place your .dat file in the root of the folder.
-Your .dat files should contain a matrix with values seperated by a space.

To execute HouseHoulder (qr_fact_househ.java)
javac qr_fact_househ.java
java qr_fact_househ

To execute Givens (qr_fact_givens.java)
javac qr_fact_givens.java
java qr_fact_givens

Each program should prompt for the name of your file. Enter the file name (including the .dat extention) and press enter.

e.g. To open a.dat, type "a.dat".

-----------------------------
3. LU and QR Solve

solve_lu_b:
Input: name of .dat file (Augmented Matrix A|b)
Output: Matrix A, L, U, ||LU - A|| Error, Vector xSol, ||Ax - b|| error

solve_qr_b:
Input: name of .dat file (Augmented Matrix A|b)
Output: Matrix A, Q, R, ||QR - A|| Error, Vector xSol, ||Ax - b|| error of Householder or Givens.


To execute LU (solve_lu_b.java)
javac solve_lu_b.java
java solve_lu_b

To execute QR (solve_qr_b.java)
javac solve_qr_b.java
java solve_qr_b

Each program should prompt for the name of your file. Enter the file name (including the .dat extention) and press enter.

e.g. To open a.dat, type "a.dat".

IMPORTANT: For QR, the progran will also prompt for you to type "HH" for householder, or "G" for givens. Please type in capital letters.

-----------------------------
4. Hilbert Matrix

hilbert_driver:
Input: size of the matrix n
Output:
LU: Matrices A, L, U, ||LU - H|| error, Vector xSol, ||Hx - b|| error
HH: Matrices A, Q, R, ||QR - H|| error, Vector xSol, ||Hx - b|| error
G:  Matrices A, Q, R, ||QR - H|| error, Vector xSol, ||Hx - b|| error

To execute Hilbert (hilbert_driver.java)
javac solve_lu_b.java
java solve_lu_b

The program should prompt for the size of the array. Enter the size of the array (int) and press enter.

-----------------------------
Convolutional Codes (Part 2)
-----------------------------
-----------------------------
1. Encoding
