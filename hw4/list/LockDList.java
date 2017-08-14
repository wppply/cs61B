// Shuangyu Li 
//8/12/17

package list;

class LockDList extends DList{
	//constructor


	public void lockNode(DListNode node){
		((LockDListNode)node).isLocked = true;

	}

	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    return new LockDListNode(item, prev, next);
  }

}