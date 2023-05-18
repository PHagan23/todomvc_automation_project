## TodoMVC Revised Test Plan
Last updated date 12/05/2023


## Objective

This test plan intends to outline the testing which will be performed to identify and resolve as many priority defects as possible within the limited time constraints. (2-3 Days)
Lower priority defects, if identified, will be evaluated for post-production resolution in collaboration with developers.
## Product Description
The product is a simple application which allows the user to create and populate "To-Do" lists in various Javascript frameworks. The purpose of the app is to allow developers to make informed decisions about which framework they may prefer to use.
These areas will primarily be tested using a combination of manual/exploratory testing and automation where appropriate.

The simple to-do list application doesn't have any persistent storage; it 
stores its data in the user's local storage (see "Features To Be Tested" for 
more information about this). Consequently, the application is not designed to 
be a real-world to-do list manager, as there's no way to share the data between 
sessions, browsers or machines.

## Scope of Testing

The main area of focus for this test is the core ToDo list functionality. Initially, we intend to test the 12 "Pure Javascript" frameworks within this test cycle.
##Outside Scope
The following items are not within the scope of this test plan and will need to be examined in subsequent sprints:
Accessibility
Mobile Devices
Touch Screen Devices
Peformance/Load
Multiple Languages
Security/Encryption

## Outside Scope

The following items are not within the scope of this test plan and will need to be examined in subsequent sprints:

Accessibility
Keyboard Navigation
Mobile Devices
Touch Screen Devices
Performance/Load
Multiple Languages
Security/Encryption

## Tools
Testing will be conducted with on a Mac though the use of a mouse, trackpad and keyboard.
Automation testing will use Selenium (java 4.9.0).

## Test Environments / Platforms
We will be running tests against the production environment (build number 193829) which can be previewed on the build server.
To mitigate the risk of individual systems influencing site behaviour - each tester will be assigned a selection of tests which they should run in each of the following browsers:
.   Firefox 113.0.1
.   Edge 113.0.1774.35
.   Safari 15.6.1
.   Chrome 113.0.5672.92

Automation appropriate tests should be cloned for each browser listed above.
Further tests will be considered for older versions of the above browsers in future test plans.

## Test Team

The team consists of four testers of equal experience.
Test distribution will aim to ensure that each of the testers explores a variety of elements to minimise scope fatigue and monotony of repetitive elements.
This will simultaneously ensure broad exposure of features to multiple users.

## Features To Be Tested

For each of the popular frameworks, we want to verify the following features:

## Core Functionality

* 1.1 Add a new ToDo item (D1, P1)
* 1.2 Check can Undo/CtrlZ (D1, P1)
* 1.3 Modify a ToDo item (by double-clicking) (D1, P1)
* 1.4 If you modify a ToDo item and click Escape during edit, it should cancel the modification (D1, P1)
* 1.5 A ToDo item can be ticked-off (it will appear with a line through it) (D1, P1)
* 1.6 A completed ToDo item can be un ticked again (D1, P1)
* 1.7 A ToDo item can be reordered by dragging it up or down in the list (we know this will fail, automate later, D4, P4)
* 1.8 Delete an incomplete ToDo item (D1, P1)
* 1.9 Delete a completed ToDo item (D1, P1)
* 1.10 Status bar displays "0 items left" when there are no items left (D1, P1)
* 1.11 Status bar displays "1 item left" when there is 1 item left (D1, P1)
* 1.12 Status bar displays "n items left" when there are >1 items left (D1, P1)
* 1.13 Status bar is hidden when there are no ToDo items in the system (D1, P1)
* 1.14 Status bar can toggle between Active, All and Completed (D1, P1)
* 1.15 When there are any completed items, a "Clear completed" link appears in the status bar (D1, P1)
* 1.16 When the "Clear completed" link is clicked, all completed items are deleted (D1, P1)
* 1.17 Clicking the down arrow symbol next to the "What needs to be done?" box will toggle all items to Completed or Not Completed (D1, P1)
* 1.18 Can we undo / rescue delete all? (D1, P1)

## Value Testing

* 2.1 Can't add an item with an empty value (D1, P1)
* 2.2 Can add a multi word-string (D1, P1)
* 2.3 ToDo items have a 280 character limit (D1, P1)
* 2.4 Cannot paste over 280 chars (D1, P1)
* 2.5 Can paste exactly 280 chars (D1, P1)
* 2.6 Check massive character count (>280) (D1, P1) (DESCOPED - Covered in test 2.4)
* 2.7 Check basic punctuation !,."? (D1, P1)
* 2.8 Check !@#%)*'\~-_ symbols (D1, P1)
* 2.9 Check primary emojos: :) :( :D :S  (D1, P1)
* 2.10 Check language native symbols: ẞ大本éñ (D1, P1)
* 2.11 Check major currencies : $£¥€ (D1, P1)

## Behaviour Testing

As well as testing within each individual to-do list application, we want to
test the behaviour across the different variants:

* ToDo items listed on any individual framework remain unique to that framework and are not present in other frameworks.

* The master list of ToDo items is stored in the browser's local storage, with
the key name `"todos-<version>"` (e.g. `todos-react` for the React version).
This key contains a comma-separated list of all the Universally Unique IDentifiers (UUIDs) for the ToDo items:

[
{"id":"6cf18c3d-29ca-45ce-8529-e6d2748a2af1","title":"hello",a"completed":false},{"id":"34a4a444-69e0-40f9-ba03-8d72dc947519","title":"goodbye","completed":false}
]

* Each individual ToDo item has its own entry in local storage with a UUID and a corresponding key containing a JSON representation of that ToDO item.

An example of the desired JSON data for an individual ToDo item can be found
below:

{
  "title": "Buy some milk",
  "priority": 3,
  "completed": false,
  "id": "b3a592f9-fbfb-6461-4eef-fba1274b5868"
}

The properties are defined as follows:

* **title** _(String)_ - The text of the ToDo item.
* **priority** _(Integer)_ - The order of the item in the ToDo list (starts at
1).
* **completed** _(Boolean)_ - Whether the ToDo item has been ticked off.
* **id** _(UUID)_ - The UUID of the ToDo item.

## Features Not To Be Tested

Within each of the TodoMVC examples, the following features should not be
tested:

* Sidebar links (Official Resources, Community, etc) for each template
* Sharability / Exporting
* Printability / Formatting


## Defect Management

When a tester encounters a bug, they will raise a defect on the GitHub Issues
page for the project. The tester should assign one of the following priorities:

* **1 - High:** Requires immediate attention
* **2 - Medium:** Should be fixed at some point today
* **3 - Low:** Should be fixed as soon as possible

Test progress will be reported daily via lunchtime standup to highlight high priority bugs. 
Any bugs deemed critical should be marked as such in Bug Report title and Product Owner tagged for attention. 
A brief end of day report produced by the Test Lead will also be shared with team.

It is the responsibility of all team members to identify and aid in resolving as many high priority bugs as possible.

## Entry Criteria

(The team assumes that earlier version testing has been conducted in alpha and that functionality outside the scope 
of this plan is already considered 'tested')

Testing will commence when:

* A build 1.0 of the `main` branch is published on the CI server.
(Having passed automated testing during publishing.)
* There are any tickets on the team's Trello board in the "Ready for Test" column.
* The Product Owner notifies the test team that testing is ready to commence.

## Completion Criteria

This test plan is considered complete when the aforementioned frameworks have been explored as per 
specification and any high priority bugs have been raised.

Scope for additional testing and framework exploration may be available within the allotted time, 
this will be discussed/prioritised during standup.

Ongoing reporting and progress monitoring should help advise the likelihood of successful completion prior to release.

In the event that testing / resolutions are not completed to a satisfactory standard prior to the scheduled release, 
product owners should confer with stakeholders regarding delaying release or shipping without full testing.

## Risks

The following table outlines all of the risks associated with this test plan, 
and how we intend to mitigate them:

| Risk	                                             | Mitigation                                                     |
|----------------------------------------------------|----------------------------------------------------------------|
| Recent intermittent internet access in our office. | Allow testers to work from remote/home location if necessary.  |
| Product Owner is on holiday for most of the week,  | Ensure PO has appointed a person who can make decisions in     |
| it may be hard to get decisions made.              | their absence.                                                 |
| Developers still making last-minute fixes while we | Ensure developers comment and notify test team of any resolved |
| test, which could invalidate our testing.          | bugs so that testers can adapt plan as needed.                 |
| We might find more bugs than the developers have   | Ensure priority attributation is closely adhered to and any    |
| time to fix.                                       | high priorities are communicated via standup/reports/end of    |
|                                                    | day reports.                                                   |
| Potential for unfinished/untested elements         | Scope of testing has been reasonably adjusted to allow         | 
|                                                    | for completion time and flexibility/adaptation of approach     |
|                                                    | (See Completion Criteria)                                      |
| Risks Beyond Scope of Plan                         | Mitigation: Specific test plans addressing these key areas will|
| Accessibility                                      | be implemented in due course.                                  | 
| Keyboard Navigation                                |                                                                |
| Mobile Devices                                     |                                                                |
| Touch Screen Devices                               |                                                                |
| Performance/Load                                   |                                                                |
| Multiple Languages                                 |                                                                |
| Security/Encryption                                |                                                                |   
| Staff Absence/Sickness                             | The timebox for test completion allows for some buffer. PO is  |
|                                                    | advised to evaluate daily and potentially look to "Borrow"     |
|                                                    | reserve testers from neighboring teams.                        |