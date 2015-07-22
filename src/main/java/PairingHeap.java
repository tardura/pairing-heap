import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Optional;


public class PairingHeap<K extends Comparable<K>, T> {

    PairingHeapNode<K, T> root;

    public PairingHeap() {
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public Optional<T> getMin(){
        return Optional.ofNullable(root == null ? null : root.data);
    }

    public void merge(PairingHeap<K, T> otherHeap){
        root = isEmpty() ? otherHeap.root : root.merge(otherHeap.root);
    }

    public void insert(K key, T dataObject){
        PairingHeapNode<K,T> newNode = new PairingHeapNode<>(key, dataObject);
        root = isEmpty() ? newNode : root.merge(newNode);
    }

    public Optional<T> deleteMin(){
        Optional<T> optionalToReturn = getMin();
        optionalToReturn.ifPresent(unusedParam -> root = combineNodes(root.child));
        return optionalToReturn;
    }

    private PairingHeapNode<K, T> combineNodes(PairingHeapNode<K,T> firstChild) {
        if(firstChild == null) return null;

        ArrayList<PairingHeapNode<K,T>> listOfSiblings = new ArrayList<>();
        for (PairingHeapNode currentlyLookingAt = firstChild; currentlyLookingAt.next != null;) {
            listOfSiblings.add(currentlyLookingAt);
            currentlyLookingAt = currentlyLookingAt.breakNextLinkAndReturnNext();
            if(currentlyLookingAt.next == null){
                listOfSiblings.add(currentlyLookingAt);
            }
        }
        PairingHeapNode<K,T>[] firstPassCombination = new PairingHeapNode[listOfSiblings.size() / 2 + listOfSiblings.size() % 2];
        for(int i = 0; i < listOfSiblings.size();){
            firstPassCombination[i/2 + i%2] = listOfSiblings.get(i).merge(listOfSiblings.get(i+1));
            i+=2;
            if(i == listOfSiblings.size() - 1){
                firstPassCombination[(i/2) + i%2] = listOfSiblings.get(i);
                i++;
            }
        }
        PairingHeapNode<K,T> newRoot = firstPassCombination.length > 0 ? firstPassCombination[0] : firstChild;
        for(int i = 1; i < firstPassCombination.length; i++){
            newRoot = newRoot.merge(firstPassCombination[i]);
        }
        return newRoot;
    }

    private class PairingHeapNode<K extends Comparable<K>, T> implements Comparable<PairingHeapNode<K, T>>{
        K key;
        T data;
        PairingHeapNode<K, T> child;
        PairingHeapNode<K, T> next;
        PairingHeapNode<K, T> previous;

        public PairingHeapNode(K keyForNode, T dataValue){
            key = keyForNode;
            data = dataValue;
            child = null;
            next = null;
            previous = null;
        }

        public int compareTo(PairingHeapNode<K, T> other) {
            return key.compareTo(other.key);
        }

        public PairingHeapNode<K,T> merge(PairingHeapNode<K,T> otherNode){
            if(this.compareTo(otherNode) <= 0){
                return otherNode.makeTheLeftMostChildOf(this);
            } else {
                return this.makeTheLeftMostChildOf(otherNode);
            }
        }

        private PairingHeapNode<K,T> makeTheLeftMostChildOf(PairingHeapNode<K, T> newLeftMostChildNode){
            if(newLeftMostChildNode == null){
                return this;
            }
            newLeftMostChildNode.previous = this.previous;
            this.previous = newLeftMostChildNode;
            this.next = newLeftMostChildNode.child;
            if(this.next != null) this.next.previous = this;
            newLeftMostChildNode.child = this;
            return newLeftMostChildNode;
        }

        private PairingHeapNode<K,T> breakNextLinkAndReturnNext(){
            PairingHeapNode<K,T> nextNode = this.next;
            this.next = null;
            this.previous = null;
            return nextNode;
        }

    }
}
