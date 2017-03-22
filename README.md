# one2oneMessagingSystem


This is a spring boot chatting application between two users and persisted in mongodb for later reference with following data model:

{
  "_id" : ObjectId("587e1c80e3959e15c08278e5"),
  "_class" : "com.eventm.domain.EventMessageModel",
  "text" : "my testing",
  "to" : "toUser",
  "from" : "fromUser",
  "createDate" : ISODate("2017-01-17T13:30:40.224Z")
}

To Write Conversation between two users
http://localhost:8080/messages
Method =POST
{"from":"fromUser","to":"toUser","t
ext":"your message here"}
Content-Type =Application/json

To READ Conversation of two users
http://localhost:8080/getMessages?from=vishu3&to=vishu2
Method =GET
Content-Type=Application/json


The client program HTML's are not matured enough , but work it out.

Access using URL’s
http://localhost:8080/login.html
Enter your id    and id of other user you want chat.







Improvement Areas
Error Handling
Pagination
Client program not up to mark(html)
DB metadata should be configurable
Lots of debug statements shall be removed

