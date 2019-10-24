# Spring Cloud Gateway RSocket Example 

This example demonstrates a client and a service. Or, int he parlance of RSocket, a requester-responder. 

Start the Gateway and then start the `requester` or `responder`. It doesn't matter, of coure, which one you use since they're both going to connect to the Gateway and if one depends on the other and the service on which it depends doesn't exist, Spring Cloud Gateway provides 100% backpressure. 
