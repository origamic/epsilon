package synthesizer;


public class CircularList<T> {
	private ListNode head = null;
	private int numberOfElements = 0;
	private ListNode actualElement = null;
	private int index = 0;

	public CircularList(T[] list) {
		for (int i = list.length - 1; i >= 0; i--) {
			insertFirst(list[i]);
		}
	}

	public boolean isEmpty() {
		return (numberOfElements == 0);
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void insertFirst(T data) {
		if (!(isEmpty())) {
			index++;
		}
		ListNode listNode = new ListNode(data, head);
		head = listNode;
		numberOfElements++;
	}

	public void insertAfterActual(T data) {
		ListNode listNode = new ListNode(data, actualElement.next);
		actualElement.next = listNode;
		numberOfElements++;
	}

	public boolean deleteFirst() {
		if (isEmpty())
			return false;
		if (index > 0)
			index--;
		head = head.next;
		numberOfElements--;
		return true;
	}

	public boolean deleteActualElement() {
		if (index > 0) {
			numberOfElements--;
			index--;
			ListNode listNode = head;
			while (listNode.next.equals(actualElement) == false)
				listNode = listNode.next;
			listNode.next = actualElement.next;
			actualElement = listNode;
			return true;
		} else {
			actualElement = head.next;
			index = 0;
			return deleteFirst();
		}
	}

	public boolean goToNextElement() {
		if (isEmpty())
			return false;
		index = (index + 1) % numberOfElements;
		if (index == 0)
			actualElement = head;
		else
			actualElement = actualElement.next;
		return true;
	}

	public T getNext() {
		goToNextElement();
		return getActualElementData();
	}

	public T getActualElementData() {
		return actualElement.data;
	}

	public void setActualElementData(T data) {
		actualElement.data = data;
	}

	class ListNode {
		public ListNode next;
		public T data;

		public ListNode(T data, ListNode next) {
			this.next = next;
			this.data = data;
		}
	}
}