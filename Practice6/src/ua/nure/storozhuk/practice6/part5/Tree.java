package ua.nure.storozhuk.practice6.part5;

public class Tree<E extends Comparable<E>> {
	Node<E> head;

	public boolean add(E element) {
		if (head == null) {
			head = new Node<>();
			head.element = element;
			head.right = null;
			head.left = null;
			return true;
		}
		if(contains(element)) {
			return false;
		}
		Node<E> temp = head;
		Node<E> prev = temp;
/*		if (temp.element.compareTo(element) > 0) {
			temp = temp.right;
		} else if (temp.element.compareTo(element) < 0) {
			temp = temp.left;
		}*/
			while (temp != null) {
				if (temp.element.compareTo(element) > 0) {
					prev = temp;
					temp = temp.right;
				} else if (temp.element.compareTo(element) < 0) {
					prev = temp;
					temp = temp.left;
				} else {
					return false;
				}
			}
			temp = new Node<>();
			temp.element = element;
			temp.right = null;
			temp.left = null;
			if (prev.element.compareTo(temp.element) > 0) {
				prev.right = temp;
			}
			if (prev.element.compareTo(temp.element) < 0) {
				prev.left = temp;
			} else {
				return false;
			}
			return true;
/*		if (temp.element.compareTo(element) < 0) {
			Node<E> prev = temp;
			temp = temp.left;
			while (temp != null) {
				if (temp.element.compareTo(element) > 0) {
					prev = temp;
					temp = temp.right;
				} else if (temp.element.compareTo(element) < 0) {
					prev = temp;
					temp = temp.left;
				} else {
					return false;
				}
			}
			temp = new Node<E>();
			temp.element = element;
			temp.right = null;
			temp.left = null;
			if (prev.element.compareTo(temp.element) > 0) {
				prev.right = temp;
			}
			if (prev.element.compareTo(temp.element) < 0) {
				prev.left = temp;
			} else {
				return false;
			}
			return true;
		}*/
	}

	public void add(E[] elements) {
		for (int m = 0; m < elements.length; m++) {
			this.add(elements[m]);
		}
	}

	public boolean contains(E element) {
		Node<E> temp = head;
		boolean res = false;
		while (!res) {
			if (temp == null) {
				break;
			}
			if (element.compareTo(temp.element) > 0) {
				temp = temp.left;
			} else if (element.compareTo(temp.element) < 0) {
				temp = temp.right;
			} else if (element == temp.element) {
				res = true;
			}
		}
		return res;
	}

	public boolean remove(E element) {
		Node<E> tmp = head;
		if (contains(element)) {
			delete(tmp, element);
			return true;
		}
		return false;
	}

	public Node<E> delete(Node<E> root, E elem) {
		if (root == null) {
			return root;
		}
		if (elem.compareTo(root.element) < 0) {
			root.right = delete(root.right, elem);
		} else if (elem.compareTo(root.element) > 0) {
			root.left = delete(root.left, elem);
		} else if (root.left != null && root.right != null) {
			root.element = minimum(root.left).element;
			root.left = delete(root.left, root.element);
		} else {
			if (root.right != null) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return root;
	}

	public Node<E> minimum(Node<E> temp) {
		if (temp.right == null) {
			return temp;
		}
		return minimum(temp.right);
	}

	public void print() {
		int spaces = 0;
		preorderPrint(head, spaces);
	}

	public void preorderPrint(Node<E> temp, int spaces) {
		if (temp == null) {
			return;
		}
		preorderPrint(temp.right, spaces + 2);
		for (int m = 0; m < spaces; m++) {
			System.out.print(" ");
		}
		System.out.println(temp.element);
		preorderPrint(temp.left, spaces + 2);
	}

	public void postorderPrint(Node<E> temp, int spaces) {
		if (temp == null) {
			return;
		}
		preorderPrint(temp.right, spaces + 2);
		preorderPrint(temp.left, spaces + 2);
		for (int m = 0; m < spaces; m++) {
			System.out.print(" ");
		}
		System.out.println(temp.element);
	}

	private static class Node<E> {
		Node<E> left;
		Node<E> right;
		E element;
	}
}
