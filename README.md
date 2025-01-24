# Complex Number Class

This repository contains a custom Java implementation of a **Complex Number Class**, designed to handle complex numbers as an extended data type. The class supports a variety of operations and ensures mathematical completeness, making it a useful tool for both academic and practical applications.

## Features

- **Euler Form Calculations**: All calculations are performed using Euler's formula, ensuring mathematical accuracy and consistency in operations.
- **Support for Multiple Representations**: The class is flexible, accepting complex numbers in various commonly used representations, including:
  - `1 + 2i`
  - `1 + 2I`
  - `1 + 2j`
  - `1 + 2J`
  
  These formats are automatically detected and handled, making the class compatible with multiple mathematical conventions.

- **Mathematical Properties**: The class adheres to key properties of complex numbers, including:
  - The identity `i * i = -1` (where `i` is the imaginary unit).
  - Support for addition, subtraction, multiplication, division, and exponentiation with complex numbers.
  - Handling of conjugates, real and imaginary parts, and modulus/argument (polar form).

## Key Features & Operations

1. **Basic Arithmetic Operations**: The class supports:
   - Addition (`+`)
   - Subtraction (`-`)
   - Multiplication (`*`)
   - Division (`/`)
   - Exponentiation (`**`)

2. **Advanced Mathematical Operations**:
   - **Powers**: Computes the powers of complex numbers.
   - **Roots**: Calculates the nth roots of a complex number.
   - **Rationalisation**: Rationalises complex numbers, converting expressions like `a + bi / c + di` into a simplified form.

3. **Brackets Support**: The calculator supports complex expressions with parentheses, allowing for grouped operations to ensure the correct order of operations.

4. **Mathematical Functions**:
   - **Conjugate**: Returns the complex conjugate of a given complex number.
   - **Modulus**: Computes the modulus (absolute value) of a complex number.
   - **Argument**: Finds the argument (angle) of the complex number in polar form.

5. **String Representation**: The class has a built-in string representation method, so complex numbers can be easily printed and displayed in the standard form.

6. **Cross-Context Compatibility**: The class can interoperate with complex numbers in a variety of formats commonly used across different academic disciplines, making it versatile for various types of calculations.

## Example Usage

```java
// Create complex numbers using different formats
ComplexNumber c1 = new ComplexNumber(1, 2);        // 1 + 2i
ComplexNumber c2 = new ComplexNumber(1, -2, 'j');  // 1 - 2j

// Perform arithmetic operations
ComplexNumber sumResult = c1.add(c2);  // Adds the two complex numbers
ComplexNumber productResult = c1.multiply(c2);  // Multiplies the two complex numbers

// Get the modulus, argument, and conjugate
double modulusC1 = c1.getModulus();
double argumentC2 = c2.getArgument();
ComplexNumber conjugateC1 = c1.getConjugate();

// Calculate powers, roots, and rationalise complex numbers
ComplexNumber powerResult = c1.pow(2);  // Raises c1 to the power of 2
ComplexNumber rootResult = c1.root(2);  // Calculates the square root of c1
ComplexNumber rationalisedResult = c1.rationalise(c2);  // Rationalises the expression c1 / c2
