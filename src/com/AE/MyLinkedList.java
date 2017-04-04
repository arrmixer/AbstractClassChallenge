package com.AE;

/**
 * Created by Angel on 12/23/16.
 */
public class MyLinkedList implements NodeList{
    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null){
            // the list was empty, so this item becomes the head of hte list
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        while(currentItem != null){
            int comparison = (currentItem.compareTo(newItem));
            if(comparison < 0){
                // newItem is greater, move right if possible
                if(currentItem.next() != null){
                    currentItem = currentItem.next();
                }else{
                    // there is no next, so insert at end of list
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            }else if(comparison > 0){
                // newItem is less, insert before
                if(currentItem.previous() != null){
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious(newItem);
                }else{
                    // the node without a previous is the root
                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            }else {
                // equal
                System.out.println(newItem.getValue() + " is already present, not added.");
                return false;
            }
        }

        return false;

    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());

        }

        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;
        while (currentItem != null) {
            int comparision = (currentItem.compareTo(item));
            //current item is less
            if (comparision > 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
                //current item is greater
            } else if (comparision > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            } else {
                //equal: we've found th item so remove it
                performRemoval(currentItem, parentItem);
                return true;
            }
            //we have reached the end of the list
            //without finding the item to delete

        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent){
            //remove item from the tree
        if(item.next() == null){
            //no right tree, so make parent point to left tree (which may be null)
            if (parent.next() == null){
                //item is right child of it parent
                parent.setNext(item.previous());
            }else if (parent.previous() == item){
                //item is left child of its parent
                parent.setPrevious(item.previous());
            }else{
                //parent must be item, which means we were looking at the root of the tree
                this.root = item.previous();
            }

        }else if(item.previous() == null){
            //no left tree, so make parent point to right tree (which may be null)
            if(parent.next() == item){
                //item is right child of it parent
                parent.setNext(item.next());
            }else if(parent.previous() == item){
                //item is left child of its parent
                parent.setPrevious(item.next());
            }else{
                //again, we are deleting th eroot
                this.root = item.next();
            }

        }else{
            //neither left nor right are null, deletion is now a lot tricker!
            //from the right sub-tree, find the smallest value (i.e., the leftmost).
            ListItem current = item.next();
        }
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null ){
            System.out.println("The list is empty");
        }else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }

    }
}
