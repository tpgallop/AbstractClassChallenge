package com.tomgallop;

public class MyLinkedList implements NodeList {
    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem item) {
        if(this.root == null) {
            // The list was empty, so this item becomes the head of the list
            this.root = item;
            return true;
        }

        ListItem currentItem = this.root;
        while(currentItem != null) {
            int comparison = (currentItem.compareTo(item));
            if(comparison < 0) {
                // Item is greater, move to the right if possible
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    // There is no next item, so insert at the end of the list
                    currentItem.setNext(item).setPrevious(currentItem);
                    return true;
                }
            } else if(comparison > 0) {
                // Item is less, insert before current item
                if(currentItem.previous() != null) {
                    currentItem.previous().setNext(item).setPrevious(currentItem.previous());
                    item.setNext(currentItem).setPrevious(item);
                } else {
                    // The node with a previous is the root
                    item.setNext(this.root).setPrevious(item);
                    this.root = item;
                }
                return true;
            } else {
                // equal
                System.out.println(item.getValue() + " is already present, not added.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if(item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;
        while(currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if(comparison == 0) {
                // Found the item to delete
                if(currentItem == this.root) {
                    this.root = currentItem.next();
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if(currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            } else if(comparison < 0) {
                currentItem = currentItem.next();
            } else {
                // Comparison > 0
                // We are at an item greater than the one to be deleted
                // So the item is not in the list
                return false;
            }
        }
        // We have reached the end of the list
        // Without finding the item to delete
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if(root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }

    }
}
