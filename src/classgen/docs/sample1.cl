package sample1;

attribute "int"      evalResult      with Expression;
attribute "boolean"  calculated      with Expression;

Expression  ::= {IntegerConstant} "int":value
              | {BinaryExpression} Expression:lhs "int":op Expression:rhs
              
Operator    ::= enum PLUS, MINUS, MULT