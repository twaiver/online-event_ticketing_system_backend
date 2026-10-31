# BIZIMANA Olivier 27582

# Online Event Ticketing System

# Steps to run my APIs

## For A2

### Step 1 For saving for buyer save and send the body with parameters
- http://locahost:8080/buyers/save

- example of the body to send {
    "firstName": "WHO CAN",
    "lastName": "MAKE IT",
    "email": "besos@gmail.com",
    "phone": "078833424"
}

### Steps 2 for getting all buyers
- http://locahost:8080/buyers/all


### Step 3 For saving for buyer update with id and body of data
- http://locahost:8080/buyers/update/{id}

- example of data you can send in the body {
    "firstName": "WHO CAN",
    "lastName": "MAKE IT",
    "email": "besos@gmail.com",
    "phone": "078833424"
}

- example of endpoint http://localhost:8080/api/buyers/update/5625b214-0de2-4e6a-94a0-105ebd22d2c9


### Steps 4 for delete the buyer with id only

- http://localhost:8080/api/buyers/delete/{id}

- example of the endpoint http://localhost:8080/api/buyers/delete/5625b214-0de2-4e6a-94a0-105ebd22d2c9


#### and make them on every domain like buyers, event-categories, venues, ticket-orders

http://localhost:8080/api/buyers/delete/5625b214-0de2-4e6a-94a0-105ebd22d2c9

## For B1 with GET Method

- http://localhost:8080/api/buyers/by-last-name?lastName=bizimana

#### this will return the buyers that have the last name equals to bizimana and egnore the cases

## For B2 with GET method
- http://localhost:8080/api/ticket-orders/by-status?status=PENDING

#### is for returning the tickets that the status equals to PENDING or PROCESSING, or PAID, or REFUNDED. Order by the order date by the newesest the first

## For B3 with GET method and we used deliverd query like find by, Between, and OrderBy
- http://localhost:8080/api/ticket-orders/between?start=2026-10-01&end=2026-10-31

#### is for returning the ticket orders that are between the date of 2026-10-01 and 2026-10-31 is like ranging

## For C1 with GET method and we used JPQL like SELECT, JOIN and LOWER
- http://localhost:8080/api/organizers/by-event-category?name=concert

#### is for returning the organizers list by the event category they have according to where clause

## For C2 with GET method with JPQL like NOT EXISTS with SELECT  in other SELECT which is subquery
- http://localhost:8080/api/venues/free 

### this is returning the venues which is empty or not being accessed

## For C3 with GET method is for JPQL for SELECT the EMPTY event categories
- http://localhost:8080/api/event-categories/unused

#### this is for returning the event categories that are not being accessed or used

## For C4 with GET method is for JPQL for DISTINCT
- http://localhost:8080/api/buyers/of-organizer/{organizerId}

#### this is for returning the buyers that are assocciated with that organizer but without duplications, where the buyers which have many ticket orders that are from that organizers, the buyer will appear once even if he/she have many ticket orders from that organizers

## For D1 is for POST method with Delivered query like existsBy with Not and And
- http://localhost:8080/api/ticket-orders/save

#### this to modify the existing methods of save ticket orders to not accept the past date and check if the organizer does not have other ticket order on that one. one ticket on day

## For D2 with PATCH method with only comparison
- http://localhost:8080/api/ticket-orders/{id}/status?status=PROCESSING

#### this the endpoint to check if the current status can accept changes or not based on what is can capable to do

## For D3 with PUT method Delivered query
- http://localhost:8080/api/organizers/{organizerId}/event-categories/{eventCategoryId}

#### this is for accepting to add the event category on the existing list of the organizer and if the organizer is already have that event do not accept it.

## For D4 with PATCH method with Delivered query
- http://localhost:8080/api/organizers/{organizerId}/venue?venueNumber=4

#### this is updating the venue to check if is available or not and check if the other organizer have that venue

## For E1 with POST method with Deliveried query

- http://localhost:8080/api/issued-tickets/save?ticketOrderId=...

#### this is for saving the issued ticket where to check the first the ticket order is available or not, or if the ticket have ticket status equal to PAID only no other else

## For E2 with GET method with delivered query
- http://localhost:8080/api/issued-tickets/buyer/{email}

#### this is to return the issued tickets which that buyer with that email issued 

## For E3 with DELETE method with delivered query of existsBy
- http://localhost:8080/api/issued-tickets/delete/{id}
- http://localhost:8080/api/ticket-orders/delete/{id}

#### this is to delete the issued ticket and ticket orders where if the id is is not available do not delete and if the ticket orders when you want to delete it and find it have the issued ticket you must delete the issued tickets first other whise is not accepting to delete while it have that issued tickets

## For F1 with GET method with JPQL for SELECT, COUNT, and GROUP BY
- http://localhost:8080/api/ticket-orders/stats/by-status

#### this is to return the list of the object of status and the number of counted rows of every status

## For F2 with PATCH method with JPQL Modifyind and Transactional for UPDATE
- http://localhost:8080/api/ticket-orders/cancel-day?organizerId=...&date=2026-10-20

#### this is to return the string of the affected rows for updating the data for REFUNDED only no other else

## For Bonus 1 with GET method with Deliveried queries and counts and countBy, and list size()
- http://localhost:8080/api/dashboard/summary

#### this the dashboard to summarize all events have been happened in the system where it count all requirements and return in Map to send it many

## For Bonues 2 with GET with delivered query with Pageable
- http://localhost:8080/api/ticket-orders/page?page=0&size=5&sort=orderDate,desc

#### this is the last endpoint where it will amke paging of data from database to return page, size of the data you want to return the sort component where it order in desc and asc.