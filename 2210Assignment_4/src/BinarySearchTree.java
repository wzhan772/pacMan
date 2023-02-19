/**
 * @Author William Zhang - 251215208 
 * The purpose of this class is to implement an ordered dictionary using a binary search tree,
 * where each node of the tree stores a Pel object, and the attribute Location of the Pel object
 * stored in a node will be its key attribute.
 */

public class BinarySearchTree implements BinarySearchTreeADT {
	private BNode root;

public BinarySearchTree() {
	//initialize new tree
	root = new BNode();
}

public BNode getRoot() {
	return root;
}

/**
 * returns the Pel object storing the given key if the key is stored in the tree
 * returns null otherwise
 */
public Pel get(BNode r, Location key) {
    //loop through all the nodes until r is a leaf (end)
while (!r.isLeaf()) {
    //returns pel if there is a match
    if (r.getData().getLocus().compareTo(key) == 0) {
        return r.getData();
    }
    //if the key is larger than the current node, go to the right child
    else if (r.getData().getLocus().compareTo(key) == -1) {
        r = r.rightChild();
    }
    //if the key is smaller than the current node, go to the left child
        else {
            r = r.leftChild();
        }
    }
    return null;
}

/**
 * inserts newData in the tree if no data item with the same key already exists
 * if a node already stores the same key, throw an exception
 */
public void put(BNode r, Pel newData) throws DuplicatedKeyException {
	//loop through all the nodes until r is a leaf (end)
while (!r.isLeaf()) {
	//throws exception if the key already exists
	if (r.getData().getLocus().compareTo(newData.getLocus()) == 0) {
		throw new DuplicatedKeyException("this key aleady exists.");
    }
	//if the key is smaller than the current node, go to the left child
	else if (r.getData().getLocus().compareTo(newData.getLocus()) == 1) {
		r = r.leftChild();
	} 
	//if the key is larger than the current node, go to the right child
	else {
		r = r.rightChild();
	}
}
//set new values for leaf and nodes
	r.setContent(newData);
	r.setLeftChild(new BNode());
	r.setRightChild(new BNode());
	r.rightChild().setParent(r);
	r.leftChild().setParent(r);
}

/**
 * removes the data item with the given key if the key is stored in the tree
 * throws an exception if the key is not stored in the tree
 */
public void remove(BNode r, Location key) throws InexistentKeyException{
    //loop through all the nodes until r is a leaf (end)
while (!r.isLeaf()) {
	//break if the key already exists
    if (r.getData().getLocus().compareTo(key) == 0) {
        break;
    }
    //if the key is smaller than the current node, go to the left child
    else if (r.getData().getLocus().compareTo(key) == 1) {
        r = r.leftChild();
    }
    //if the key is larger than the current node, go to the right child
    else {
        r = r.rightChild();
    }
}
//if the current node is not a leaf
if(!r.isLeaf()) {
	//if both children are leaves, remove each respective node
    if (r.leftChild().isLeaf() && r.rightChild().isLeaf()) {
    	//if the right child is a leaf and needs to be removed
        if (r.rightChild().isLeaf()) {
        	//set new parent and left nodes
            BNode parent = r.parent();
            BNode nodeLeft = r.leftChild();
            //if the parent node is null, set the new root to be the left node
            if (parent == null) { 
                root = nodeLeft;
                //if the left node is not a leaf then set the parent to null
                if (!nodeLeft.isLeaf()) {
                	nodeLeft.setParent(null);
                }
            }
            else {
            	//if the left child equals the node then set the left child to the left node
                if (parent.leftChild().equals(r)) {
                	parent.setLeftChild(nodeLeft);
                }
                //otherwise set the right child to the right node
                else {
                	parent.setRightChild(nodeLeft);
                }
            }
        }
        //if the left child is a leaf and needs to be removed 
        if (r.leftChild().isLeaf()) { 
        	//set new parent and right nodes
            BNode parent = r.parent();
            BNode nodeRight = r.rightChild();
            //if the parent node is null, set the new root to be the right node
            if (parent == null) {
                root = nodeRight;
                //if the right node is not a leaf then set the parent to null
                if (!nodeRight.isLeaf()) {
                	nodeRight.setParent(null);
                }
            }
            else {
            	//if the left child equals the node then set the left child to the left node
                if (parent.leftChild().equals(r)) {
                	parent.setLeftChild(nodeRight);
                }
                //otherwise set the right child to the right node
                else {
                	parent.setRightChild(nodeRight);
                }
            }
        }
    }
    //if both children are not leaves (or 1)
    else {
    	//set new node to be the right child
    	BNode newNode = r.rightChild();
    	//while the new node is not a leaf, set it to be the left child
        while (!newNode.isLeaf()) {
        	newNode = newNode.leftChild();
        }
        //set new content
        r.setContent(newNode.parent().getData());
    }
}
//throw exception
    else {
    	throw new InexistentKeyException(null);
    }
}

/**
 * returns the Pel object with the smallest key larger than the given one
 * returns null if the given key has no successor
 */
public Pel successor(BNode r, Location key) {
    //loop through until r either is a leaf or the successor exists already
while (true) {
    if (r.isLeaf()) {
        break;
    }
    else {
        //if the key is smaller than the current node, go to the left child
        if (r.getData().getLocus().compareTo(key) == 1) {
            r = r.leftChild();
        //if the key is larger than the current node, go to the right child
        }
        else if (r.getData().getLocus().compareTo(key) == -1) {
            r = r.rightChild();
        }
        else {
            break;
        }
    }
}
//alter r value to its parent value and look for successor
if (r.isLeaf()) {
    r = r.parent();
}
//if the right child is a leaf
if (r.rightChild().isLeaf()) {
	//while the parent is not null then search from node r to find where the parent node does not host r as a child
    BNode parentNode = r.parent();
    while (parentNode != null) {
        if (!parentNode.leftChild().isLeaf() && parentNode.leftChild().getData().getLocus().compareTo(r.getData().getLocus()) == 0) {
            return parentNode.getData();
        }
        //set new parent node
        r = parentNode;
        parentNode = parentNode.parent();
    }
}
else { 
    return r.rightChild().getData();
}
//null if no successor
    return null;
}

/**
 * return the Pel object with the largest key smaller than the given one
 * returns null if the given key has no predecessor
 */
public Pel predecessor(BNode r, Location key) {
    //loop through until r either is a leaf or the successor exists already
while (true) {
	if (r.isLeaf()) {
		break;
	}
	else {
        //if the key is smaller than the current node, go to the left child
		if (r.getData().getLocus().compareTo(key) == 1) {
			r = r.leftChild();
            //if the key is larger than the current node, go to the right child
		} 
		else if (r.getData().getLocus().compareTo(key) == -1) {
			r = r.rightChild();
		} 
		else {
			break;
		}
	}
}
//alter r value to its parent value and look for predecessor
if (r.isLeaf()) {
    r = r.parent();
}
//if the left child is a leaf
if (r.leftChild().isLeaf()) {
	//while the parent is not null then search from node r to find where the parent node does not host r as a child
	BNode parentNode = r.parent();
	while (parentNode != null) {
		if (!parentNode.rightChild().isLeaf()
				&& parentNode.rightChild().getData().getLocus().compareTo(r.getData().getLocus()) == 0) {
			return parentNode.getData();
		}
		//set new parent node
		r = parentNode;
		parentNode = parentNode.parent();
	}
}
else { 
	return r.leftChild().getData();
}
//null if no predecessor
	return null;
}

/**
 * returns the Pel object in the tree with the smallest key
 * throws an exception if the tree has no data
 */
public Pel smallest(BNode r) throws EmptyTreeException {
	EmptyTreeException emptyTreeException = new EmptyTreeException("the tree is currently empty");
//if r is a leaf (end), throw exception
if (r.isLeaf()) {
	throw emptyTreeException;
} 
else {
	//create a new placeholder for the root
	BNode placeholder = r;
	//ensure that the left child is not a leaf to continue down the tree
	while(placeholder.leftChild().isLeaf() != true) {
		//set placeholder to be the value of the left child
		placeholder = placeholder.leftChild();
	}
	//return the value
		return placeholder.getData();
	}
}

/**
 * returns the Pel object in the tree with the largest key
 * throws an exception if the tree has no data
 */
public Pel largest(BNode r) throws EmptyTreeException {
	EmptyTreeException emptyTreeException = new EmptyTreeException("The tree is currently empty");
//if r is a leaf (end), throw exception
if (r.isLeaf()) {
	throw emptyTreeException;
} 
else {
	//create a new placeholder for the root
	BNode placeholder = r;
	//ensure that the right child is not a leaf to continue down the tree
	while (placeholder.rightChild().isLeaf() != true) {
		//set placeholder to be the value of the left child
		placeholder = placeholder.rightChild();
	}
	//return the value
	return placeholder.getData();
	}
}
}