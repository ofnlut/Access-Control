//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Access Control
// Files:           AccessControl.java, User.java, AccessControlTest.java
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

import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {


  // Local Controller variables
  private static ArrayList<User> users;
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to
                                                             // new users or when we reset a user's
                                                             // password.



  /*
   * Launch an AccessControl instance
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }

  // A no-parameter constructor
  AccessControl() {

    currentUser = null;
    users = new ArrayList<User>();
    users.add(new User("admin", "root", true));

  }

  /*
   * Report whether a given username/password pair is a valid login
   * 
   * @param username of login request
   * 
   * @param password of associated username
   */
  public static boolean isValidLogin(String username, String password) {
    boolean returnValue = false;
    for (int i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username) && users.get(i).isValidLogin(password)) {
        returnValue = true;
      }
    }
    return returnValue;
  }

  /*
   * Change the current user's password
   * 
   * @param newPassword being set to the current user
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /*
   * Log out the current user
   * 
   */
  public void logout() {
    currentUser = null;
  }

  /*
   * A mutator you can use to write tests without simulating user input
   * 
   * @param username of user being logged in
   */
  public void setCurrentUser(String username) {
    for (int i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username)) {
        currentUser = users.get(i);
      }
    }
  }


  /*
   * Create a new userWith the default password and isAdmin==false.
   * 
   * @param username of new user
   */

  public boolean addUser(String username) {
    for (int i = 0; i < users.size(); ++i) {
      if (currentUser == null || users.get(i).getUsername().equals(username)
          || currentUser.getIsAdmin() == false) {
        return false;
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, false));
    return true;
  }

  /*
   * Create a new user and specify their admin status.
   * 
   * @param username of new user
   * 
   * @param isAdmin flag to determine if user is granted admin powers.
   */
  public boolean addUser(String username, boolean isAdmin) {
    for (int i = 0; i < users.size(); i++) {
      if (currentUser == null || users.get(i).getUsername().equals(username)
          || currentUser.getIsAdmin() == false) {
        return false;
      }
    }
    users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
    return true;
  }

  /*
   * Remove a user (names should be unique)
   * 
   * @param username of user getting removed.
   */
  public boolean removeUser(String username) {
    // Check if currentUser has admin status
    if (!currentUser.getIsAdmin() || currentUser == null) {
      return false;
    } else {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) { // Find the user object
          users.remove(i); // Remove user from users ArrayList
        }
      }
      return true;
    }
  }

  /*
   * Give a user admin power
   * 
   * @param username of user receiving admin powers.
   */
  public boolean giveAdmin(String username) {
    // Check if currentUser has admin status
    if (!currentUser.getIsAdmin() || currentUser == null) {
      return false;
    } else {
      for (int i = 0; i < users.size(); i++) { // Find the user object
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(true); // Set the user admin status to true
        }
      }
      return true;
    }
  }

  /*
   * Remove a user's admin power
   * 
   * @param username of user getting their admin power revoked.
   */
  public boolean takeAdmin(String username) {
    if (!currentUser.getIsAdmin() || currentUser == null) {
      return false;
    } else {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(false);
        }
      }
      return true;
    }
  }

  /*
   * Reset a user's password
   * 
   * @param username of user resetting their password
   */
  public boolean resetPassword(String username) {

    // Check if currentUser has admin status
    if (!currentUser.getIsAdmin() || currentUser == null) {
      return false;
    } else {
      for (int i = 0; i < users.size(); i++) { // Find the user object
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setPassword(DEFAULT_PASSWORD); // Set the user object password to
                                                      // DEFAULT_PASSWORD
        }
      }
      return true;
    }
  }


  /*
   * Main driver loop. Prompt the user for login information calls the isValidLogin method If the
   * login is valid, call the sessionScreen method
   * 
   * @param userInputScanner to take in arguments
   */
  public void loginScreen(Scanner userInputScanner) {
    while (true) {

      // Login prompt
      System.out.println("Login\n------------");
      System.out.print("Username: ");
      String usernameInput = userInputScanner.next(); // Input username
      System.out.print("Password: ");
      String passwordInput = userInputScanner.next(); // Input password
      System.out.println();

      userInputScanner.nextLine();

      // Pass in login information
      boolean validity = isValidLogin(usernameInput, passwordInput);
      if (validity) {
        sessionScreen(usernameInput, userInputScanner);
      } else {
        System.err.println("Invalid username or password, try again.");
      }

    }

  }


  /*
   * Set the currentUser Allows them to changePassword or logout If they are an admin, gives access
   * to admin methods
   * 
   * @param username object sets the currentUser
   * 
   * @param userInputScanner to take in arguments
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    boolean programRun = true;

    while (programRun == true) {
      // Set currentUser to login input
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {
          currentUser = users.get(i);
        }
      }

      // Helper variables
      String newPassword;
      String newUsername;
      String adminCommand;
      String[] commandToArray;

      // Admin user command set
      if (currentUser.getIsAdmin() == true) {
        System.out.println("========Administative Access========");
        System.out.println("User: " + currentUser.getUsername() + "\n");
        System.out.println("Commands:");
        System.out.println("    logout");
        System.out.println("    newpw [newpassword]");
        System.out.println("    adduser [username]");
        System.out.println("    adduser [username] [true or false]");
        System.out.println("    rmuser [username]");
        System.out.println("    giveadmin [username]");
        System.out.println("    rmadmin [username]");
        System.out.println("    resetpw [username]");
        System.out.println("What would you like to do?");

        adminCommand = userInputScanner.nextLine();

        commandToArray = new String[3];
        commandToArray = adminCommand.trim().split(" ");

        /*
         * Switch set takes in array values option and runs associated method
         */
        switch (commandToArray[0]) {

          case "logout":
            logout();
            programRun = false;
            System.out.println("Logged out!");
            break;
          case "newpw":
            newPassword = commandToArray[1];
            changePassword(newPassword);
            break;
          case "adduser":
            newUsername = commandToArray[1];
            if (commandToArray.length == 3) {
              boolean isAdmin = Boolean.valueOf(commandToArray[2]);
              if (addUser(newUsername, isAdmin) == false) {
                System.err.println("User already exist, try again.");
              }
            } else {
              if (addUser(newUsername) == false) {
                System.err.println("User already exist, try again.");
                continue;
              }
            }
            break;
          case "rmuser":
            newUsername = commandToArray[1];
            removeUser(newUsername);
            break;
          case "giveadmin":
            newUsername = commandToArray[1];
            giveAdmin(newUsername);
            break;
          case "rmadmin":
            newUsername = commandToArray[1];
            takeAdmin(newUsername);
            break;
          case "resetpw":
            newUsername = commandToArray[1];
            resetPassword(newUsername);
            break;
        }
      } else { 
        
        // Non-admin user command set
        System.out.println("=======Welcome Back=======");
        System.out.println("User: " + currentUser.getUsername());
        System.out.println("logout");
        System.out.println("newpw [newpassword]\n");
        System.out.println("What would you like to do?");

        adminCommand = userInputScanner.nextLine();
        commandToArray = new String[3];
        commandToArray = adminCommand.split(" ");
        switch (commandToArray[0]) {
          case "logout":
            logout();
            programRun = false;
            System.out.println("Logged out!");
            break;
          case "newpw":
            newPassword = commandToArray[1];
            changePassword(newPassword);
            break;
        }
      }
    }
  }
}


