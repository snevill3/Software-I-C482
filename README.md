# Software I-C482

## Course Overview
<p>Software I builds object-oriented programming expertise and introduces powerful new tools for Java application development. 
You will learn about and put into action class design, exception handling, and other object-oriented principles 
and constructs to develop software that meets business requirements. This course requires foundational knowledge of object-oriented programming and the Java language.</p>

<b>Competencies</b>
<ul>
<li>Classes and Interfaces</li>
<li>Object-Oriented Principles</li>
<li>Application Development</li>
<li>Exception Handling</li>
<li>User Interface Development</li>
</ul>

## Objective Assessment/Project
### Introduction
<p>Throughout your career in software design and development, you will be asked to create applications with various features and functionality based on business requirements. 
When a new system is developed, typically the process begins with a business analyst gathering and writing these business requirements, 
with the assistance of subject matter experts from the business. Then a system analyst works with several application team members and others to formulate a solution 
based on the requirements. As a developer, you would then create a design document from the solution and finally develop the system based on your design document. 
For this assessment, you will create a Java application using the solution statements provided in the requirements section.</p>
<p><b>Note:</b> The preferred integrated development environment (IDE) for this assignment is NetBeans 8.2 or IntelliJ (Community Edition). 
Use the web links below to install one of these IDEs (Please note the IntelliJ link is a direct download). If you choose to use another IDE, 
you must export your project into NetBeans 8.2 or IntelliJ format for submission.
Your submission should include a zip file with all the necessary code files to compile, support, and run your application. The zip file submission must also keep 
the project file and folder structure intact for the IDE.
</p>

### Scenario
<p>You are working for a small manufacturing organization that has outgrown its current inventory system. 
They have been using a spreadsheet program to manually enter inventory additions, deletions, and other data from a paper-based system but would now 
like you to develop a more sophisticated inventory program. They have provided you with a mock-up of the user interface to use in the design and development 
of the system (see the attached “GUI Mock-Up”) and a class diagram to assist you in your work (see the attached “UML Class Diagram”). 
The organization also has specific business requirements that must be included as part of the application. A system analyst from your company created the solution 
statements outlined in the requirements section based on the manufacturing organization’s business requirements. You will use these solution statements to develop your 
application.
</p>

### Requirements
<p>Your submission must be your original work. No more than a combined total of 30% of the submission and no more than a 10% match to any one individual source 
can be directly quoted or closely paraphrased from sources, even if cited correctly. An originality report is provided when you submit your task that can be used as a guide. 
You must use the rubric to direct the creation of your submission because it provides detailed criteria that will be used to evaluate your work. 
Each requirement below may be evaluated by more than one rubric aspect. The rubric aspect titles may contain hyperlinks to relevant portions of the course.
</p>
I. <b><i>User Interface</i></b>
<p>Create a JavaFX application with a graphical user interface (GUI) based on the attached “GUI Mock-Up”. Write code to display each  of the following screens in the GUI: </p>
A. A main screen, showing the following controls:
<ul>
<li>buttons for “Add”, “Modify”, “Delete”, “Search” for parts and products, and “Exit”</li>
<li>lists for parts and products</li>
<li>text boxes for searching for parts and products</li>
<li>title labels for parts, products, and the application title</li>
</ul>

B. An add part screen, showing the following controls:
<ul>
<li>radio buttons for “In-House” and “Outsourced” parts</li>
<li>buttons for “Save” and “Cancel”</li>
<li>text fields for ID, name, inventory level, price, max and min values, and company name or machine ID</li>
<li>labels for ID, name, inventory level, price/cost, max and min values, the application title, and company name or machine ID</li>
</ul>

C. A modify part screen, with fields that populate with pre-saved data, showing the following controls:
<ul>
<li>radio buttons for “In-House” and “Outsourced” parts</li>
<li>buttons for “Save” and “Cancel”</li>
<li>text fields for ID, name, inventory level, price, max and min values, and company name or machine ID</li>
<li>labels for ID, name, inventory level, price, max and min values, the application title, and company name or machine ID</li>
</ul>

D. An add product screen, showing the following controls:
<ul>
<li>buttons for “Save”, “Cancel”, “Add” part, and “Delete” part</li>
<li>text fields for ID, name, inventory level, price, and max and min values</li>
<li>labels for ID, name, inventory level, price, max and min values, and the application</li>
<li>a list for associated parts for this product</li>
<li>a “Search” button and a text field with an associated list for displaying the results of the search</li>
</ul>

E.  A modify product screen, with fields that populate with pre-saved data, showing the following controls:
<ul>
<li>buttons for “Save”, “Cancel”, “Add” part, and “Delete” part</li>
<li>text fields for ID, name, inventory level, price, and max and min values</li>
<li>labels for ID, name, inventory level, price, max and min values, and the application</li>
<li>a list for associated parts for this product</li>
<li>a “Search” button and a text field with associated list for displaying the results of the search</li>
</ul>

II. <b><i>Application</i></b>
<p>Now that you’ve created the GUI, write code to create the class structure provided in the attached “UML (unified modeling language) Class Diagram”. 
Enable each  of the following capabilities in the application:</p>
F.  Using the attached “UML Class Diagram”, create appropriate classes and instance variables with the following criteria:
<ul>
<li>five classes with the 16 associated instance variables</li>
<li>variables are only accessible through getter methods</li>
<li>variables are only modifiable through setter methods</li>
</ul>

G. Add the following functionalities to the main screen, using the methods provided in the attached “UML Class Diagram”:
<ul>
<li>redirect the user to the “Add Part”, “Modify Part”, “Add Product”, or “Modify Product” screens</li>
<li>delete a selected part or product from the list</li>
<li>search for a part or product and display matching results</li>
<li>exit the main screen</li>
</ul>

H.  Add the following functionalities to the part screens, using the methods provided in the attached “UML Class Diagram”:
<ol>
<li>
<ul>
“Add Part” screen
<li>select “In-House” or “Outsourced”</li>
<li>enter name, inventory level, price, max and min values, and company name or machine ID</li>
<li>save the data and then redirect to the main screen</li>
<li>cancel or exit out of this screen and go back to the main </li>
</ul>
</li>
<li>
<ul>
“Modify Part” screen
<li>select “In-House” or “Outsourced”</li>
<li>modify or change data values</li>
<li>save modifications to the data and then redirect to the main screen</li>
<li>cancel or exit out of this screen and go back to the main screen</li>
</ul>
</li>
</ol>

I.  Add the following functionalities to the product screens, using the methods provided in the attached “UML Class Diagram”:
<ol>
<li>
“Add Product” screen
<ul>
<li>enter name, inventory level, price, and max and min values</li>
<li>save the data and then redirect to the main screen</li>
<li>associate one or more parts with a product</li>
<li>remove or disassociate a part from a product</li>
<li>cancel or exit out of this screen and go back to the main screen</li>
</ul>
</li>
<li>
“Modify Product” screen
<ul>
<li>modify or change data values</li>
<li>save modifications to the data and then redirect to the main screen</li>
<li>associate one or more parts with a product</li>
<li>remove or disassociate a part from a product</li>
<li>cancel or exit out of this screen and go back to the main screen</li>
</ul>
</li>
</ol>

J.  Write code to implement exception controls with custom error messages for one requirement out of each of the following sets (pick one from each):
<ol>
<li>
Set 1
<ul>
<li>entering an inventory value that exceeds the minimum or maximum value for that part or product</li>
<li>preventing the minimum field from having a value above the maximum field</li>
<li>preventing the maximum field from having a value below the minimum field</li>
<li>ensuring that a product must always have at least one part</li>
</ul>
</li>
<li>
Set 2
<ul>
<li>including a confirm dialogue for all “Delete” and “Cancel” buttons</li>
<li>ensuring that the price of a product cannot be less than the cost of the parts</li>
<li>ensuring that a product must have a name, price, and inventory level (default 0)</li>
</ul>
</li>
</ol>

K.  Demonstrate professional communication in the content and presentation of your submission.

<ul>
<b>File Restrictions</b>
<li>File name may contain only letters, numbers, spaces, and these symbols: ! - _ . * ' ( )</li>
<li>File size limit: 200 MB</li>
<li>File types allowed: doc, docx, rtf, xls, xlsx, ppt, pptx, odt, pdf, txt, qt, mov, mpg, avi, mp3, wav, mp4, wma, flv, asf, mpeg, wmv, m4v, svg, tif, tiff, 
jpeg, jpg, gif, png, zip, rar, tar, 7z
</li>
</u>
