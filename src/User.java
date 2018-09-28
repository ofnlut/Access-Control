//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Access Control
// Files:           User.java, AccessControl.java, AccessControlTest.java
// Course:          CS300, Fall, 2018
//
// Author:          Devin Mozee
// Email:           mozee@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Joziah Mays
// Partner Email:   jmays2@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x_ Write-up states that pair programming is allowed for this assignment.
//   _x_ We have both read and understand the course Pair Programming Policy.
//   _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class User {

  private final String USERNAME; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers


  /*
   * User object created
   * 
   * @param username of new user object
   * 
   * @param password of new user object
   * 
   * @param isAdmin status of new user object
   */
  public User(String username, String password, boolean isAdmin) {

    USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /*
   * Report whether the password is correct
   * 
   * @param password of user
   */
  public boolean isValidLogin(String password) {
    if (!this.password.equals(password)) {
      return false;
    }
    return true;
  }

  // Return the user's name
  public String getUsername() {
    return USERNAME;
  }

  // Report whether the user is an admin
  public boolean getIsAdmin() {
    if (isAdmin != true) {
      return false;
    }
    return true;
  }

  // Set the new password
  public void setPassword(String password) {
    this.password = password;
  }

  // Set the new admin status
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
