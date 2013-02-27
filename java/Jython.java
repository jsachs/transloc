// Class to invoke TransLoc python code from java

import org.python.util.PythonInterpreter; 
import org.python.core.*; 

public class Jython {

	public static void main(String[] args) 
		throws PyException
	{
        PythonInterpreter interp = new PythonInterpreter();

        System.out.println("Hello, brave new world");
        interp.exec("import sys");
        interp.exec("print sys");
        interp.exec("print sys.path");

        interp.set("a", new PyInteger(42));
        interp.exec("print a");
        interp.exec("x = 2+2");
        PyObject x = interp.get("x");

        System.out.println("x: "+x);
        System.out.println("Goodbye, cruel world");

        PySystemState sys = Py.getSystemState();
        sys.path.append(new PyString("/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7"));
        interp.exec("print sys.path");

        interp.execfile("../python/main.py");
	}
}