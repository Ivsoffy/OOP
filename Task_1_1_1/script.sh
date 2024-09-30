javac src/main/java/ru/nsu/ivchenko/HeapSort.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.ivchenko
java -cp ./build ru.nsu.ivchenko.HeapSort