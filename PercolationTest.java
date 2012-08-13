/**
 * Name: Raj Sahae
 * Date: 8/12/2012
 * Week 1 Assignment: Percolation
 * 
 * To use this class, run as a JUnit test, for example, from the command line:
 * "java org.junit.runner.JUnitCore PercolationTest"
 */

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PercolationTest {

  private Percolation p;

  @Before
  public void setUp() {
    p = new Percolation(5);
  }

  @Test
  public void testIsOpen() {
    p.open(1, 1);
    assertTrue(p.isOpen(1, 1));
    p.open(3, 4);
    assertTrue(p.isOpen(3, 4));
    p.open(4, 5);
    assertFalse(p.isOpen(5, 4));
  }

  @Test
  public void testIsFull() {
    p.open(1, 1);
    p.open(2, 1);
    p.open(3, 1);
    p.open(4, 1);
    p.open(5, 1);
    assertTrue(p.isFull(1, 1));
    assertTrue(p.isFull(2, 1));
    assertTrue(p.isFull(3, 1));
    assertTrue(p.isFull(4, 1));
    assertTrue(p.isFull(5, 1));
    assertFalse(p.isFull(1, 2));
    assertFalse(p.isFull(2, 3));
    assertFalse(p.isFull(4, 5));
    assertFalse(p.isFull(1, 3));
    assertFalse(p.isFull(1, 5));
  }

  @Test
  public void testPercolates() {
    p.open(1, 3);
    p.open(2, 3);
    p.open(2, 4);
    p.open(3, 4);
    p.open(4, 4);
    p.open(4, 3);
    p.open(4, 2);

    assertFalse(p.percolates());

    p.open(5, 2);

    assertTrue(p.percolates());
  }

  // All methods should throw a java.lang.IndexOutOfBoundsException
  //   if i or j are outside the bounds.
  @Test (expected = IndexOutOfBoundsException.class)
  public void testOpenBoundsExceptionZeroRow() {
    p.open(0, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testOpenBoundsExceptionOverRow() {
    p.open(6, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testOpenBoundsExceptionZeroCol() {
    p.open(3, 0);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testOpenBoundsExceptionOverCol() {
    p.open(3, 6);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsOpenBoundsExceptionZeroRow() {
    p.isOpen(0, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsOpenBoundsExceptionOverRow() {
    p.isOpen(6, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsOpenBoundsExceptionZeroCol() {
    p.isOpen(3, 0);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsOpenBoundsExceptionOverCol() {
    p.isOpen(3, 6);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsFullBoundsExceptionZeroRow() {
    p.isFull(0, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsFullBoundsExceptionOverRow() {
    p.isFull(6, 3);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsFullBoundsExceptionZeroCol() {
    p.isFull(3, 0);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIsFullBoundsExceptionOverCol() {
    p.isFull(3, 6);
  }
}
