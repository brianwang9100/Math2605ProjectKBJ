--------------------------------------------------------------------------------
Math2605Project
Brian Wang
Wei Wang
Karl Nicodemus

--------------------------------------------------------------------------------
Hilbert (Part 1)
--------------------------------------------------------------------------------
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

Please place your .dat file in the root of the folder.
Your .dat files should contain a matrix with values seperated by a space.

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
5. Discussion

Please open the file: HilbertDiscussion.pdf to view the discussion

--------------------------------------------------------------------------------
Convolutional Codes (Part 2)
--------------------------------------------------------------------------------
-----------------------------
1. Encoding

encoding:
Input: size of stream x
Output: stream y

To execute Encoding (encoding.java)
javac encoding.java
java encoding

The program should prompt for the size of the stream. Please input the size of the desired stream (int). The program should randomly generate a stream x and output its encoded stream y.

-----------------------------
2. Jacobi and Gauss-Seidel algorithm

jacobi:
Input: name of .dat file for augmented matrix A|b, name of .dat file for vector x0, and tolerance in DECIMAL FORM.
Output: number of iterations, solution Vector x

gauss_seidel
Input: name of .dat file for augmented matrix A|b, name of .dat file for vector x0, and tolerance in DECIMAL FORM.
Output: number of iterations, solution Vector x

To execute Jacobi (jacobi.java)
javac jacobi.java)
java jacobi

To execute Gauss-Siedel (gauss_seidel.java)
javac gauss_seidel.java)
java gauss_seidel

Each program should prompt for the name of your files for A|b and x0. Enter the file name (including the .dat extention) and press enter.
e.g. To open a.dat, type "a.dat".

Then the program should prompt for the tolerance number. Enter the tolerance in DECIMAL format.

e.g. 0.00000001 NOT 10^-8.

IMPORTANT: By default the programs are run in base 10.

-----------------------------
3. Decoding with Jacobi and Gauss-Seidel

decoding:
Input: stream y
Output: stream x (with Jacobi), stream x (with Gauss-Siedel)

To execute Decoding (decoding.java)
javac decoding.java
java decoding

The program should prompt for a stream. Please type out the the stream seperating each term with a space. The program should output its decoded stream x with jacobi and gauss-seidel.

e.g input = "10 00 01 11 00 11 10 01 10 11 00 01"

IMPORTANT: By default the program is run in binary.

-----------------------------
4. Discussion
Please open the file: ConvolutionalCodesDiscussion.pdf

--------------------------------------------------------------------------------
Convolutional Codes (Part 2)
--------------------------------------------------------------------------------
-----------------------------
1. Power Method

power_method:
Input: name of .dat file for n x n matrix, tolerance level in decimal format
Output: number of iterations, eigenvalue, eigenvector

To execute PowerMethod (decoding.java)
javac power_method.java
java power_method

Each program should prompt for the name of your file. Enter the file name (including the .dat extention) and press enter.

e.g. To open a.dat, type "a.dat".

Then the program should prompt for the tolerance number. Enter the tolerance in DECIMAL format.

e.g. 0.00000001 NOT 10^-8.

The program uses initial u0 of (1, 1, 1, ...., 1)^t

-----------------------------
2. Dicussion
Please open the file: UrbanPopulationDynamics.pdf
