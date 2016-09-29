package org.dsm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    @org.junit.Test
    public void test()
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long batchNo = Long.valueOf(sdf.format(new Date()) + "001");
		System.out.println(batchNo);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
