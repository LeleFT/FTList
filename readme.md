FTList
======

A Java library for displaying a list of objects and four facility buttons

It offers two kind of list.
- <b>FTSimpleList:</b> a simple list of objects backed by a JList.
- <b>FTTableList:</b> a list of objects backed by a JTable so you can display multiple fields with a header.

Facility buttons can be placed in one of the four positions defined in SwingConstants (see below) and each
button can be hidden/displayed separately. SwingConstants position for the buttons:
- <b>SwingConstants.NORTH:</b> buttons are placed in the NORTH part of the component.
- <b>SwingConstants.EAST:</b> buttons are placed in the EAST part of the component.
- <b>SwingConstants.SOUTH:</b> buttons are placed in the SOUTH part of the component.
- <b>SwingConstants.WEST:</b> buttons are placed in the WEST part of the component.

Button's purposes are, respectively:
- Add a new object to the list
- Remove selected objects from the list
- Move selected object UP in the list
- Move selected object DOWN in the list

Listeners can be registered to the list in order to receive events from the buttons clicked.
A class that is interested in receive such events must implements the <code>FTActionListener</code> interface
and register using the <code>addFTActionListener</code> method.
