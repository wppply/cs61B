//Shuangyu Li
//08/12/17
package list;

class LockDListNode extends DListNode{
	protected boolean isLocked;

	LockDListNode(object i, DListNode a, DListNode b){
		super(i,a,b);
		isLocked =false;
	}
}