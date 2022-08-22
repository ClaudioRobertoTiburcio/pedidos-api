<h1>pedidos-api</h1>

<h2>This is my first api.</h2>

<h2>The technologies I'm using are:</h2>

<table>
 <tr>
  <td>Java</td>
  <td>PostgreSQL</td>
 </tr>
 <tr>
  <td>8</td>
  <td>12</td>
 </tr>
</table>

<br>

<h3>How to Run the project</h3>
<pre>All you have to do is install postgreSQL 12 on your machine. 
Make sure postgres is running in his default port "5432".
Set the username to: <strong>postgres</strong> and the password to: <strong>root</strong>. 
<em>Do not forget to create a Schema named: "public"</em>
(All of those configurations can be changed in the application.properties file)

Depending on your IDE, you might have to install a plugin for Lombok
Here's the link: https://projectlombok.org/download

Also make sure that you have jdk 8 installed. After that, just pull the project and run it.</pre>

<h2>Endpoints</h2>
<pre>
 <h3>Get Requests (Read and List)</h3>
     /api/items
     /api/items/{itemId}
     /api/orders
     /api/orders/{orderId}
     /api/orders/{orderId}/items/
     /api/orders/{orderId}/items/{orderItemId}
     
     
<h3>Post Requests (Create)</h3>
    /api/items 
    body{
       description: string,
       value: double,
       type: char
    }
    
    /api/orders
    body{
       number: integer,
       date: timestamp,
       percentualDiscount: double
    }
    
    /api/orders/{orderId}/items
    body{
       itemId: UUID,
       quantity: double
    }
    
    
 <h3>Put Requests (Update)</h3>
    /api/items/{itemId}
    body{
       description: string,
       value: double,
       type: char
    }
    
    /api/orders/
    body{
       number: integer,
       date: timestamp,
       percentualDiscount: double,
       totalValue: double
    }
    
    /api/orders/{orderId}/items/{orderItemId}
    body{
       itemId: UUID,
       quantity: double
    }
    
    
 <h3>Delete Requests</h3>
    /api/items/{itemId}
    /api/orders/{orderId}
    /api/orders/{orderId}/items/{orderItemId}
</pre>

<p>I wasn't able to manage a request when I had the orderItens class related to the two others, that's why the tables won't have any foreign key constraint. 
So if you how to do it, open a pull request and explain what and why you did the changes. Also, I would appreciate feedback for the project and the readme.</p>



