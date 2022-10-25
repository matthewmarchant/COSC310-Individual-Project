# COSC310-group-project
Our project is an inventory management system for a retail store. The system will have 3 main components, a central database which keeps track of all information, 
an interface to be used by cashiers which can make transactions and update the database, and a more in-depth interface to be used by a manager to manage stock, 
input new orders, view sales stats and stock warnings, etc. The main features that we need to implement are a login system for entering credentials to access either 
the manager or cashier screen, a cashier system that can process sales, and a management system that can add products, view sales, and manage suppliers.

Instructions to compile and run the code: 






The whole system is divided into  five main classes which handle a singular core function of the system. The Cashier class handles
the cashier functions such as scanning the items of the customer's cart and the payment. The Login class manages the login for both
the cashiers and the manager. The manage employees class gives the manager the access to manage employees of the store. The manager
class gives manager the manager options to check the sale statistics, Inventory and low stocks. The suppliers class shows the list
of suppliers for that store set by the manager.

The cashier class contains methods to add an item, delete an item, to logout of the system and to record the sale made to the database.
The login class has two methods one for the manager login and one for the cashier login. Both the methods register the cashier and the 
manager within a databse and uses that database to verify the credentials to login successfully.

The manage employees class has a method which has a list of all the employees connected to the database. With the updateList method
the manager can update the list of employees. The class also has methods for logging out and going to the home screen.

The supplier class has methods to add and delete suppliers and the class is connected to the database which can be accessed locally

Lastly, the manager class has several methods which are easy to interpret such as to manage sales, the inventory and the low stock alerts
warning. The manager can add item and delte items in the inventory through respective methods.
