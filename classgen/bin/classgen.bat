@echo off

if not "%OS%"=="Windows_NT" goto win9xStart

@setlocal

:win9xStart

if not "%CLASSGEN_HOME%"=="" goto checkJava

echo CLASSGEN_HOME is not set. Please set CLASSGEN_HOME.
goto end

:checkJava
if not "%JAVA_HOME%"=="" goto runClassgen

echo JAVA_HOME is not set. Please set JAVA_HOME.
goto end

:runClassgen
set LOCALCLASSPATH=%CLASSGEN_HOME%\lib\classgen.jar;%CLASSGEN_HOME%\lib\java-cup-10k-b2-runtime.jar

"%JAVA_HOME%\bin\java" -classpath "%LOCALCLASSPATH%" classgen.Main %1 %2 %3 %4 %5 %6 %7 %8 %9

:end
set LOCALCLASSPATH=

if not "%OS%"=="Windows_NT" goto mainEnd
@endlocal

:mainEnd
