JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES =\
	main.java
all:classes
classes: $(CLASSES:.java=.class)
clean:
	rm -f  *.class