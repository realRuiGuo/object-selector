/*
 * ObjectSelector - Object selection library for Java
 * Copyright (C) 2013 - 2016 Fabian Prasser
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import de.linearbits.objectselector.Selector;
import de.linearbits.objectselector.SelectorBuilder;
import de.linearbits.objectselector.util.IntArrayAccessor;
import de.linearbits.objectselector.util.ObjectAccessor;

/**
 * Some JUnit tests
 * @author Fabian Prasser
 *
 */
public class Tests extends TestCase {
    
    @SuppressWarnings("unused")
    private List<Integer> result;

    @Test
    public void test1() throws IllegalArgumentException, IOException, ParseException {

        List<Element> elements = getElements();

        // Create a selector with the builder pattern
        Selector<Element> selector = new SelectorBuilder<Element>(new ElementAccessor())
                                        .begin()
                                            .field("bool").equals(true)
                                            .and()
                                            .field("integer").geq(50)
                                        .end()
                                        .or()
                                        .field("numeric").leq(30d)
                                        .build();

        // Select
        List<Integer> list1 = getSelected(selector, elements);

        // Create a selector by parsing a query string
        selector = new SelectorBuilder<Element>(new ElementAccessor(), 
                                                "('bool'='true' and 'integer'>='50') or 'numeric'<='30'")
                                                .build();
    	
        // Select
        List<Integer> list2 = getSelected(selector, elements);
        
        // Test
        assertTrue(list1.equals(list2));
    }
    
    @Test
    public void test2() {

        try {
            List<Element> elements = getElements();

            // Create a selector by parsing a query string
            Selector<Element> selector = new SelectorBuilder<Element>(new ElementAccessor(), 
                                                    "('bool'='true' and 'integer'>='50') or 'numeric'<='30'")
                                                    .build();
            
            // Select
            result = getSelected(selector, elements);
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void test3() {

        try {
            List<Element> elements = getElements();

            // Create a selector with the builder pattern
            Selector<Element> selector = new SelectorBuilder<Element>(new ElementAccessor())
                                                        .begin()
                                                            .field("bool").equals(true)
                                                            .and()
                                                            .field("integer").geq(50)
                                                        .end()
                                                        .or()
                                                        .field("numeric").leq(30d)
                                                        .build();

            // Select
            result = getSelected(selector, elements);
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test4() throws IllegalArgumentException, IOException, ParseException {

        List<Element> elements = getElements();

        // Create a selector with the builder pattern
        Selector<Element> selector = new SelectorBuilder<Element>(new ObjectAccessor<Element>(Element.class))
                                        .begin()
                                            .field("bool").equals(true)
                                            .and()
                                            .field("integer").geq(50)
                                        .end()
                                        .or()
                                        .field("numeric").leq(30d)
                                        .build();
    	

        // Select
        List<Integer> list1 = getSelected(selector, elements);

        // Create a selector by parsing a query string
        selector = new SelectorBuilder<Element>(new ObjectAccessor<Element>(Element.class),
                                              "('bool'='true' and 'integer'>='50') or 'numeric'<='30'")
                                              .build();
    	
        // Select
        List<Integer> list2 = getSelected(selector, elements);
        
        // Test
        assertTrue(list1.equals(list2));
    }

    @Test
    public void test5() {

        try {
            List<Element> elements = getElements();

            // Create a selector with the builder pattern
            Selector<Element> selector = new SelectorBuilder<Element>(new ObjectAccessor<Element>(Element.class))
                                                                        .begin()
                                                                            .field("bool").equals(true)
                                                                            .and()
                                                                            .field("integer").geq(50)
                                                                        .end()
                                                                        .or()
                                                                        .field("numeric").leq(30d)
                                                                        .build();
            

            // Select
            result = getSelected(selector, elements);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test6()  {

        try {
            List<Element> elements = getElements();

            // Create a selector by parsing a query string
            Selector<Element> selector = new SelectorBuilder<Element>(new ObjectAccessor<Element>(Element.class),
                                                  "('bool'='true' and 'integer'>='50') or 'numeric'<='30'")
                                                  .build();
            
            // Select
            result = getSelected(selector, elements);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test7() throws IllegalArgumentException, IOException, ParseException {

		List<int[]> elements = getIntArrays();
		
		// Create header
		IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

		// Create a selector with the builder pattern
		Selector<int[]> selector = new SelectorBuilder<int[]>(accessor).field("field1").leq(20)
                                                                       .and()
                                                                       .field("field2").leq(20)
                                                                       .and()
                                                                       .field("field3").leq(20)
                                                                       .build();

        // Select
        List<Integer> list1 = getSelected(selector, elements);

		// Create a selector by parsing a query string
		selector = new SelectorBuilder<int[]>(accessor, 
		                                      "'field1'<='20' and 'field2'<='20' and 'field3'<='20'")
		                                      .build();

        // Select
        List<Integer> list2 = getSelected(selector, elements);
        
        // Test
        assertTrue(list1.equals(list2));
    }

    @Test
    public void test8() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector with the builder pattern
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor).field("field1").leq(20)
                                                                           .and()
                                                                           .field("field2").leq(20)
                                                                           .and()
                                                                           .field("field3").leq(20)
                                                                           .build();

            // Select
            result = getSelected(selector, elements);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test9() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1'<='20' and 'field2'<='20' and 'field3'<='20'")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test10() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1'<>'20' and 'field2'<>'20' and 'field3'<>'20'")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    

    @Test
    public void test11() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1'<>'20'<>'field1'")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
            fail("This string must not compile");
        } catch (Exception e) {
            // Expected behavior
        }
    }

    @Test
    public void test12() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1' <> '20' '20'")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
            fail("This string must not compile");
        } catch (Exception e) {
            // Expected behavior
        }
    }
    

    @Test
    public void test13() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1'<>'20'<>'20'")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
            fail("This string must not compile");
        } catch (Exception e) {
            // Expected behavior
        }
    }
    
    @Test
    public void test14() {

        try {
            List<int[]> elements = getIntArrays();
            
            // Create header
            IntArrayAccessor accessor = new IntArrayAccessor(new String[]{"field1", "field2", "field3"});

            // Create a selector by parsing a query string
            Selector<int[]> selector = new SelectorBuilder<int[]>(accessor, 
                                                  "'field1'<>'20'<>")
                                                  .build();

            // Select
            result = getSelected(selector, elements);
            fail("This string must not compile");
        } catch (Exception e) {
            // Expected behavior
        }
    }
    
    /**
     * Create a set of 1000 pseudo-random elements
     * @return
     */
    private List<Element> getElements() {
        // Create a set of 1000 elements
    	Random random = new Random(0xDEADBEEF);
    	List<Element> elements = new ArrayList<Element>();
        for (int i=0; i<1000; i++){
            elements.add(Element.getRandomElement(random));
        }
        return elements;
    }

    /**
     * Create a set of 1000 pseudo-random int-arrays
     * @return
     */
    private List<int[]> getIntArrays() {
		Random random = new Random(0xDEADBEEF);
		List<int[]> elements = new ArrayList<int[]>();
		for (int i=0; i<1000; i++){
			elements.add(Element.getRandomIntArray(random));
		}
        return elements;
    }
    
    /**
     * Selection process
     * @param selector
     * @param list
     * @return
     */
    private<T> List<Integer> getSelected(Selector<T> selector, List<T> list){
    	List<Integer> result = new ArrayList<Integer>();
        for (int i=0; i<list.size(); i++) {
            final T entry = list.get(i);
            if (selector.isSelected(entry)) {
            	result.add(i);
            }
        }
        return result;
    }
}