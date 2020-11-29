
import java.text.DecimalFormat;
import java.util.Scanner;

public class HighOutput {
    private static DecimalFormat df = new DecimalFormat("0.000");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        long currunt = System.currentTimeMillis();
        CyberSpace cyberSpace = new CyberSpace(null, 0);
        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt();
            SocialNetWork socialNetWork = new SocialNetWork(null, null, null, 0, 0);
            for (int j = 0; j < m; j++) {
                String s = scanner.next();
                socialNetWork.addSubject(new Subject(s, j, null));
            }
            int n = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                String name = scanner.next();
                Person person = new Person(name, j, null, null, null);
                int f = scanner.nextInt();
                for (int k = 0; k < f; k++) {
                    String z = scanner.next();
                    int x = z.indexOf(":");
                    int v = Integer.parseInt(z.substring(0, x));
                    double b = Double.parseDouble(z.substring(x + 1));
                    person.addSubjectInterest(new SubjectIntrest(getSubject(socialNetWork, v), b, null));
                }
                socialNetWork.addPerson(person);
            }
            int r = scanner.nextInt();
            for (int j = 0; j < r; j++) {
                int q = scanner.nextInt();
                int w = scanner.nextInt();
                for (int k = 0; k < w; k++) {
                    String z = scanner.next();
                    int x = z.indexOf(":");
                    int v = Integer.parseInt(z.substring(0, x));
                    double b = Double.parseDouble(z.substring(x + 1));
                    Person first = getPerson(socialNetWork, q);
                    Person target = getPerson(socialNetWork, v);
                    first.addPersonInterest(new PersonInterest(target, b, null));
                }
            }
            cyberSpace.addNetwork(socialNetWork);
        }

        cyberSpace.orgenize();
//
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            Question question = new Question(i, null, 0, null);
            int qw = scanner.nextInt();
            for (int j = 0; j < qw; j++) {
                String we = scanner.next();

                Subject sd = new Subject(we, subjectIndex(cyberSpace, we), null);
                question.addSubject(sd);
            }
            int depth = scanner.nextInt();
            question.setDepth(depth);
            cyberSpace.addQuestion(question);
        }


        cyberSpace.initializeMatrixes();

        cyberSpace.processQuestions();
    }

    public static Subject getSubject(SocialNetWork so, int i) {
        Subject s = so.getFirstSubject();
        while (s.getIndex() != i) {
            s = s.getNext();
        }
        return s;
    }

    public static int subjectIndex(CyberSpace so, String st) {
        Subject t = so.firstSubject();
        int i = 0;
        while (t != null) {
            if (t.getName().equals(st)) {
                return i;
            }
            i++;
            t = t.getNext();
        }
        return 0;
    }

    public static Person getPerson(SocialNetWork so, int i) {
        Person s = so.getFirstPerson();
        while (s.getIndex() != i) {
            s = s.getNext();
        }
        return s;
    }
}

class ColList2 {
    private ColNode2 first;
    private int size;


    public ColList2(ColNode2 first, int size) {
        this.first = first;
        this.size = size;
    }

    public int Size() {
        return size;
    }

    private boolean isEmpty() {
        return Size() == 0;
    }

    public ColNode2 First() {
        if (isEmpty()) {
            return null;
        }
        return first;
    }

    private ColNode2 Last() {
        if (isEmpty()) {
            return null;
        } else {
            ColNode2 n = First();
            while (n.getNext() != null) {
                n = n.getNext();
            }
            return n;
        }
    }

    public void add(RowList2 x, int colNum) {
        ColNode2 node = new ColNode2(colNum, x, null);
        if (isEmpty()) {
            first = node;
        } else {
            ColNode2 last = Last();
            last.setNext(node);
        }
        size++;
    }


    public ColList2 clone(ColList2 colList2) {
        ColList2 list = new ColList2(null, 0);
        ColNode2 n = colList2.First();
        while (n != null) {
            list.add(n.getRowList2().clone(n.getRowList2()), n.getColNum());
            n = n.getNext();
        }
        return list;
    }

    @Override
    public String toString() {
        return "ColList2{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }
}

class NoSushFile extends RuntimeException {

    public NoSushFile(String message) {
        super(message);
    }
}

class ColNode2 {
    private int colNum;
    private RowList2 rowList2;
    private ColNode2 next;

    public ColNode2(int colNum, RowList2 rowList2, ColNode2 next) {
        this.colNum = colNum;
        this.rowList2 = rowList2;
        this.next = next;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public RowList2 getRowList2() {
        return rowList2;
    }

    public void setRowList2(RowList2 rowList2) {
        this.rowList2 = rowList2;
    }

    public ColNode2 getNext() {
        return next;
    }

    public void setNext(ColNode2 next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ColNode2{" +
                "colNum=" + colNum +
                ", rowList2=" + rowList2 +
                ", next=" + next +
                '}';
    }
}

class RowList2 {
    private RowNode2 first;
    private int size;

    public RowList2(RowNode2 first, int size) {
        this.first = first;
        this.size = size;
    }

    public int Size() {
        return size;
    }

    public boolean isEmpty() {
        return Size() == 0;
    }

    public RowNode2 First() {
        if (isEmpty()) {
            return null;
        }
        return first;
    }


    public RowNode2 Last() {
        if (isEmpty()) {
            return null;
        } else {
            RowNode2 n = First();
            while (n.getNext() != null) {
                n = n.getNext();
            }
            return n;
        }
    }

    public void add(double x, int rowNum) {
        RowNode2 node = new RowNode2(rowNum, x, null);
        if (isEmpty()) {
            first = node;
        } else {
            RowNode2 last = Last();
            last.setNext(node);
        }
        size++;
    }

    public static RowList2 clone(RowList2 rowList2) {
        RowList2 list = new RowList2(null, 0);
        RowNode2 n = rowList2.First();
        while (n != null) {
            list.add(n.getValue(), n.getRowNumber());
            n = n.getNext();
        }
        return list;
    }

    @Override
    public String toString() {
        return "RowList2{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }
}

class RowNode2 {
    private int rowNumber;
    private double value;
    private RowNode2 next;

    public RowNode2(int colNumber, double value, RowNode2 next) {
        this.rowNumber = colNumber;
        this.value = value;
        this.next = next;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public RowNode2 getNext() {
        return next;
    }

    public void setNext(RowNode2 next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "RowNode2{" +
                "rowNumber=" + rowNumber +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}

class CyberSpace {
    private SocialNetWork first;
    private int size;
    private Subject firstSubject;
    private Person firstPerson;
    private int personSize;
    private int subjectSize;
    private MyMatrix friendship;
    private MyMatrix interests;
    private Question firstQuestion;
    private int questionSize;
    private String names = "";

    public CyberSpace(SocialNetWork first, int size) {
        this.first = first;
        this.size = size;
    }

    public SocialNetWork firstNetwork() {
        return first;
    }

    public int networkSize() {
        return size;
    }

    public boolean networkIsEmpty() {
        return networkSize() == 0;
    }

    public SocialNetWork lastNetwork() {
        if (networkIsEmpty()) {
            return null;
        } else {
            SocialNetWork r = firstNetwork();
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public void addNetwork(SocialNetWork socialNetWork) {
        if (networkIsEmpty()) {
            first = socialNetWork;
        } else {
            SocialNetWork last = lastNetwork();
            last.setNext(socialNetWork);
        }
        size++;
    }

    public Person firstPerson() {
        return firstPerson;
    }

    public int personSize() {
        return personSize;
    }

    public boolean personIsEmpty() {
        return personSize() == 0;
    }

    public Person lastPerson() {
        if (personIsEmpty()) {
            return null;
        } else {
            Person r = firstPerson();
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public void addPerson(Person person) {
        if (personIsEmpty()) {
            firstPerson = person;
        } else {
            Person r = firstPerson();
            while (r.getNext() != null) {
                if (r.getName().equals(person.getName())) {
                    return;
                }
                r = r.getNext();
            }
            if (r.getName().equals(person.getName())) {
                return;
            }
            r.setNext(person);
        }
        personSize++;
    }

    public Subject firstSubject() {
        return firstSubject;
    }

    public int subjectSize() {
        return subjectSize;
    }

    public boolean subjectIsEmpty() {
        return subjectSize() == 0;
    }

    public Subject lastSubject() {
        if (subjectIsEmpty()) {
            return null;
        } else {
            Subject r = firstSubject();
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public void addSubject(Subject subject) {
        if (subjectIsEmpty()) {
            firstSubject = subject;
        } else {
            Subject s = firstSubject();
            while (s.getNext() != null) {
                if (s.getName().equals(subject.getName())) {
                    return;
                }
                s = s.getNext();
            }
            if (s.getName().equals(subject.getName())) {
                return;
            }
            s.setNext(subject);
        }
        subjectSize++;
    }

    public Question firstQuestion() {
        return firstQuestion;
    }

    public int questionSize() {
        return questionSize;
    }

    public boolean questionIsEmpty() {
        return questionSize == 0;
    }

    public Question lastQuestion() {
        if (questionIsEmpty()) {
            return null;
        } else {
            Question r = firstQuestion;
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public void addQuestion(Question question) {
        if (questionIsEmpty()) {
            firstQuestion = question;
        } else {
            Question s = firstQuestion;
            while (s.getNext() != null) {
                s = s.getNext();
            }
            s.setNext(question);
        }
        questionSize++;
    }


    public void orgenize() {
        SocialNetWork s = firstNetwork();
        while (s != null) {
            Person p = s.getFirstPerson();
            while (p != null) {
                addPerson(new Person(p.getName(), personSize, p.getFirstPerson(), p.getFirstSubject(), null));
                p = p.getNext();
            }
            Subject k = s.getFirstSubject();
            while (k != null) {
                addSubject(new Subject(k.getName(), subjectSize, null));
                k = k.getNext();
            }
            s = s.getNext();
        }
    }

    public void initializeMatrixes() {
        interests = new MyMatrix(null, null, personSize, subjectSize);
        friendship = new MyMatrix(null, null, personSize, personSize);
        RowList li = new RowList(null, 0);
        RowList rl = new RowList(null, 0);
        for (int i = 0; i < personSize; i++) {
            li.add(new ColList(null, 0), i);
            rl.add(new ColList(null, 0), i);
        }
        SocialNetWork snw = firstNetwork();
        while (snw != null) {
            Person f = snw.getFirstPerson();
            while (f != null) {
                RowNode zt = findPersonRow(li, findPersonCol(f));
                RowNode zt2 = findPersonRow(rl, findPersonCol(f));
                ColList zx = zt.getColList();
                ColList zx2 = zt2.getColList();

                SubjectIntrest si = f.getFirstSubject();
                while (si != null) {
                    int mn = findSubjectCol(si.getSubject());
                    zx.addOrUpdate(si.getValue(), mn);
                    si = si.getNext();
                }

                PersonInterest pi = f.getFirstPerson();
                while (pi != null) {
                    int mn = findPersonCol(pi.getTarget());

                    zx2.addOrUpdate(pi.getValue(), mn);
                    pi = pi.getNext();
                }
                f = f.getNext();
            }
            snw = snw.getNext();
        }
        interests.setRowList(li);
        friendship.setRowList(rl);

//        MatrixConverter.RowToColConverter(interests);
    }

    private RowNode findPersonRow(RowList li, int personCol) {
        RowNode zx = li.First();
        while (zx != null) {
            if (zx.getRowNum() == personCol) {
                return zx;
            }
            zx = zx.getNext();
        }
        return null;
    }

    private int findSubjectCol(Subject subject) {
        Subject f = firstSubject;
        while (f != null) {
            if (f.getName().equals(subject.getName())) {
                return f.getIndex();
            }
            f = f.getNext();
        }
        return 0;
    }

    private int findPersonCol(Person person) {
        Person f = firstPerson;
        while (f != null) {
            if (f.getName().equals(person.getName())) {
                return f.getIndex();
            }
            f = f.getNext();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "CyberSpace{" +
                "firstQuestion=" + firstQuestion +
                '}';
    }

    public void processQuestions() {
        Question q = firstQuestion;
        while (q != null) {
            names = "";
            int i = 0;
            MyMatrix inte = SpecialClone(interests, q);
            while (i <= q.getDepth()) {
                OutputList l = takeOutResponses(inte, i);
//                System.out.println(l);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                OutputList l2 = OutputList.sort(l);
                PrintValues(l2);
                i++;
                inte = MatrixMultiplication.multiplication(friendship, inte);
            }
            q = q.getNext();
        }
    }

    private void PrintValues(OutputList l2) {
        OutputNode f = l2.getFirstNode();
        while (f != null) {
            StringBuilder k = new StringBuilder();
            names = names + f.getName();
            k.append(f.getName() + " ");
            k.append(String.format("%6f", f.getValue()) + " ");
            for (int i = 0; i < f.getDepth(); i++) {
                k.append("+");
            }
            System.out.println(k);
            f = f.getNext();
        }
    }

    private OutputList takeOutResponses(MyMatrix inte, int depth) {
        OutputList l = new OutputList();
        RowNode rn = inte.getRowList().First();
        while (rn != null) {
            ColList cl = rn.getColList();
            if (!cl.isEmpty()) {
                ColNode s = cl.First();
                while (s != null) {
                    String name = findName(rn.getRowNum());
                    if (!names.contains(name)) {
                        OutputNode fg = new OutputNode(name, s.getValue(), depth);
                        OutputNode.addNode(fg, l);
                    }
                    s = s.getNext();
                }
            }
            rn = rn.getNext();
        }
        return l;
    }

    public MyMatrix SpecialClone(MyMatrix matrix, Question q) {
        RowNode rn = matrix.getRowList().First();
        RowList newlist = new RowList(null, 0);
        for (int i = 0; i < personSize; i++) {
            newlist.add(new ColList(null, 0), i);
        }
        RowNode rn2 = newlist.First();
        while (rn != null) {
            if (rn.getColList().Size() >= q.getSize()) {
                ColNode cl = rn.getColList().First();
                double newnode = 0.0;
                int i = 0;
                while (cl != null) {
                    Subject s = q.getFirstSubject();
                    while (s != null) {
                        if (cl.getColNumber() == s.getIndex()) {
                            newnode = newnode + cl.getValue();
                            i++;
                            break;
                        }
                        s = s.getNext();
                    }
                    cl = cl.getNext();
                }
                if (i == q.getSize()) {

                    rn2.getColList().add(newnode, 0);
                }
            }
            rn = rn.getNext();
            rn2 = rn2.getNext();
        }

        MyMatrix res = new MyMatrix(newlist, null, matrix.getM(), matrix.getN());
        return res;
    }


    public String findName(int i) {
        Person s = firstPerson;
        int j = 0;
        while (s != null) {
            if (j == i) {
                return s.getName();
            }
            j++;
            s = s.getNext();
        }
        return "";
    }
}

class OutputList {
    private OutputNode firstNode;
    private int size;

    public int Size() {
        return size;
    }

    public boolean IsEmpty() {
        return size == 0;
    }


    public static OutputList sort(OutputList s) {
//        System.out.println("***********"+  s );
        OutputNode f = s.getFirstNode();
        OutputList l = new OutputList();
        while (f != null) {
//            System.out.println("****** " + f + "******");
            OutputNode.addNode(f, l);
            f = f.getNext();
        }
        return l;
    }

    public OutputNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(OutputNode firstNode) {
        this.firstNode = firstNode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "OutputList{" +
                "firstNode=" + firstNode +
                '}';
    }
}

class OutputNode implements Comparable {
    private String name;
    private double value;
    private int depth;
    private OutputNode next;

    public OutputNode(String name, double value, int depth) {
        this.name = name;
        this.value = value;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public OutputNode getNext() {
        return next;
    }

    public void setNext(OutputNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "OutputNode{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", next=" + next +
                '}';
    }

    public static void addNode(OutputNode n, OutputList l) {
        if (l.IsEmpty()) {
            l.setFirstNode(n);
            l.setSize(l.getSize()+1);
        } else {
            OutputNode s = l.getFirstNode();
            OutputNode t = null;
            while (s != null) {
                if (n.getValue() > s.getValue()) {
                    n.setNext(s);
                    if (t == null) {
                        l.setFirstNode(n);
                    } else {
                        t.setNext(n);
                    }
                    l.setSize(l.getSize() + 1);
                    break;
                }
                else if (n.getValue() == s.getValue()) {
                    if (n.getName().compareTo(s.getName()) < 0) {
                        n.setNext(s);
                        if (t == null) {
                            l.setFirstNode(n);
                        } else {
                            t.setNext(n);
                        }
                        l.setSize(l.getSize() + 1);
                        break;
                    }
                }
                else {
                    if (s.getNext() == null) {
                        s.setNext(n);
                        l.setSize(l.getSize() + 1);
                        break;
                    }
                }
                t = s;
                s = s.getNext();
            }
        }
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class Person {
    private String name;
    private int index;
    private PersonInterest firstPerson;
    private int personSize;
    private SubjectIntrest firstSubject;
    private int subjectSize;
    private Person next;

    public Person(String name, int index, PersonInterest firstPerson, SubjectIntrest firstSubject, Person next) {
        this.name = name;
        this.index = index;
        this.firstPerson = firstPerson;
        this.firstSubject = firstSubject;
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public PersonInterest getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(PersonInterest firstPerson) {
        this.firstPerson = firstPerson;
    }

    public SubjectIntrest getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(SubjectIntrest firstSubject) {
        this.firstSubject = firstSubject;
    }

    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }

    private PersonInterest FirstInterest() {
        return firstPerson;
    }

    private SubjectIntrest FirstSubject() {
        return firstSubject;
    }

    private PersonInterest lastInterest() {
        if (firstPerson == null) {
            return null;
        } else {
            PersonInterest p = FirstInterest();
            while (p.getNext() != null) {
                p = p.getNext();
            }
            return p;
        }
    }

    private SubjectIntrest lastSubject() {
        if (firstSubject == null) {
            return null;
        } else {
            SubjectIntrest p = FirstSubject();
            while (p.getNext() != null) {
                p = p.getNext();
            }
            return p;
        }
    }

    public void addPersonInterest(PersonInterest personInterest) {
        if (firstPerson == null) {
            firstPerson = personInterest;
        } else {
            PersonInterest last = lastInterest();
            last.setNext(personInterest);
        }
        personSize++;

    }

    public void addSubjectInterest(SubjectIntrest subjectIntrest) {
        if (firstSubject == null) {
            firstSubject = subjectIntrest;
        } else {
            SubjectIntrest last = lastSubject();
            last.setNext(subjectIntrest);
        }
        subjectSize++;

    }

    public int getPersonSize() {
        return personSize;
    }

    public void setPersonSize(int personSize) {
        this.personSize = personSize;
    }

    public int getSubjectSize() {
        return subjectSize;
    }

    public void setSubjectSize(int subjectSize) {
        this.subjectSize = subjectSize;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", next=" + next +
                '}';
    }
}

class PersonInterest {
    private Person target;
    private double value;
    private PersonInterest next;

    public PersonInterest(Person target, double value, PersonInterest next) {
        this.target = target;
        this.value = value;
        this.next = next;
    }

    public Person getTarget() {
        return target;
    }

    public void setTarget(Person target) {
        this.target = target;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public PersonInterest getNext() {
        return next;
    }

    public void setNext(PersonInterest next) {
        this.next = next;
    }
}

class Question {
    private int index;
    private Subject firstSubject;
    private int depth;
    private int size;
    private Question next;


    public Question(int index, Subject firstSubject, int depth, Question next) {
        this.index = index;
        this.firstSubject = firstSubject;
        this.depth = depth;
        this.next = next;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Subject getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(Subject firstSubject) {
        this.firstSubject = firstSubject;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Question getNext() {
        return next;
    }

    public void setNext(Question next) {
        this.next = next;
    }

    private int Size() {
        return size;
    }

    private boolean IsEmpty() {
        return size == 0;
    }

    private Subject lastSubject() {
        if (IsEmpty()) {
            return null;
        }
        Subject s = firstSubject;
        while (s.getNext() != null) {
            s = s.getNext();
        }
        return s;
    }

    public void addSubject(Subject subject) {
        if (IsEmpty()) {
            firstSubject = subject;
        } else {
            Subject last = lastSubject();
            last.setNext(subject);
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Question{" +
                "index=" + index +
                ", firstSubject=" + firstSubject +
                ", depth=" + depth +
                ", size=" + size +
                ", next=" + next +
                '}';
    }
}

class SocialNetWork {
    private Person firstPerson;
    private Subject firstSubject;
    private SocialNetWork next;
    private int personSize;
    private int subjectSize;

    public SocialNetWork(Person firstPerson, Subject firstSubject, SocialNetWork next, int personSize, int subjectSize) {
        this.firstPerson = firstPerson;
        this.firstSubject = firstSubject;
        this.next = next;
        this.personSize = personSize;
        this.subjectSize = subjectSize;
    }

    public Person getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(Person firstPerson) {
        this.firstPerson = firstPerson;
    }

    public Subject getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(Subject firstSubject) {
        this.firstSubject = firstSubject;
    }

    public SocialNetWork getNext() {
        return next;
    }

    public void setNext(SocialNetWork next) {
        this.next = next;
    }

    public int getPersonSize() {
        return personSize;
    }

    public void setPersonSize(int personSize) {
        this.personSize = personSize;
    }

    public int getSubjectSize() {
        return subjectSize;
    }

    public void setSubjectSize(int subjectSize) {
        this.subjectSize = subjectSize;
    }

    public Person FirstPerson() {
        return firstPerson;
    }

    public Subject FirstSubject() {
        return firstSubject;
    }

    public Person LastPerson() {
        if (firstPerson == null) {
            return null;
        } else {
            Person r = FirstPerson();
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public Subject LastSubject() {
        if (firstSubject == null) {
            return null;
        } else {
            Subject r = FirstSubject();
            while (r.getNext() != null) {
                r = r.getNext();
            }
            return r;
        }
    }

    public void addPerson(Person person) {
        if (firstPerson == null) {
            firstPerson = person;
        } else {
            Person last = LastPerson();
            last.setNext(person);
        }
        personSize++;
    }

    public void addSubject(Subject subject) {
        if (firstSubject == null) {
            firstSubject = subject;
        } else {
            Subject last = LastSubject();
            last.setNext(subject);
        }
        subjectSize++;
    }

}

class Subject {
    private String name;
    private int index;
    private Subject next;

    public Subject(String name, int index, Subject next) {
        this.name = name;
        this.index = index;
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Subject getNext() {
        return next;
    }

    public void setNext(Subject next) {
        this.next = next;
    }


}

class SubjectIntrest {
    private Subject subject;
    private double value;
    private SubjectIntrest next;

    public SubjectIntrest(Subject subject, double value, SubjectIntrest next) {
        this.subject = subject;
        this.value = value;
        this.next = next;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SubjectIntrest getNext() {
        return next;
    }

    public void setNext(SubjectIntrest next) {
        this.next = next;
    }
}

class MatrixCalculations {

    public static MyMatrix clone(MyMatrix myMatrix) {
        RowList list = myMatrix.getRowList().clone(myMatrix.getRowList());
        ColList2 list2 = myMatrix.getColList().clone(myMatrix.getColList());
        MyMatrix myMatrix1 = new MyMatrix(list, list2, myMatrix.getM(), myMatrix.getN());
        return myMatrix1;
    }

    private static ColList add1(ColList a, ColList b) {
        ColList list = new ColList(null, 0);
        ColNode a1 = a.First();
        ColNode b1 = b.First();
        while (a1 != null || b1 != null) {
            if (a1 != null && b1 != null && a1.getColNumber() == b1.getColNumber()) {
                list.add(a1.getValue() + b1.getValue(), a1.getColNumber());
                a1 = a1.getNext();
                b1 = b1.getNext();
            } else if (a1 != null && (b1 == null || a1.getColNumber() < b1.getColNumber())) {
                list.add(a1.getValue(), a1.getColNumber());
                a1 = a1.getNext();
            } else {
                list.add(b1.getValue(), b1.getColNumber());
                b1 = b1.getNext();
            }
        }
        return list;
    }

    private static RowList add2(RowList a, RowList b) {
        RowList list = new RowList(null, 0);
        RowNode a1 = a.First();
        RowNode b1 = b.First();
        while (a1 != null || b1 != null) {
            if (a1 != null && b1 != null && a1.getRowNum() == b1.getRowNum()) {
                list.add(add1(a1.getColList(), b1.getColList()), a1.getRowNum());
                a1 = a1.getNext();
                b1 = b1.getNext();
            } else if (a1 != null && (b1 == null || a1.getRowNum() < b1.getRowNum())) {
                list.add(a1.getColList(), a1.getRowNum());
                a1 = a1.getNext();
            } else {
                list.add(b1.getColList(), b1.getRowNum());
                b1 = b1.getNext();
            }
        }
        return list;
    }
}

class MatrixConverter {
    public static MyMatrix RowToColConverter(MyMatrix matrix) {
        ColList2 list = new ColList2(null, 0);
        for (int i = 0; i < matrix.getN(); i++) {
            list.add(new RowList2(null, 0), i);
        }
        RowList list1 = matrix.getRowList();
        RowNode a = list1.First();
        while (a != null) {
            ColList s = a.getColList();
            if (!s.isEmpty()) {
                ColNode t = s.First();
                while (t != null) {
                    addToCol(list, t.getValue(), t.getColNumber(), a.getRowNum());
                    t = t.getNext();
                }
            }
            a = a.getNext();
        }
        matrix.setColList(list);
        return matrix;
    }

    private static void addToCol(ColList2 list, double x, int i, int j) {
        ColNode2 a = list.First();
        while (a.getColNum() != i) {
            a = a.getNext();
        }
        RowList2 t = a.getRowList2();
        t.add(x, j);
    }
}

class MatrixMultiplication {

    private static DecimalFormat df = new DecimalFormat("0.000000");

    public static MyMatrix multiplication(MyMatrix a, MyMatrix b) {
        if (a.getN() != b.getM()) {
            throw new NoSushFile("Invalid operation.");
        } else {
            MatrixConverter.RowToColConverter(b);
            MyMatrix matrix = createEmptyRowMatrix(a.getM(), b.getN());
            RowList a1 = a.getRowList();
            RowList a2 = matrix.getRowList();
            ColList2 b1 = b.getColList();
            RowNode ra = a1.First();
            RowNode ra2 = a2.First();
            while (ra != null) {
                ColNode2 rb = b1.First();
                while (rb != null) {
                    double s = mul(ra, rb);
                    if (s != 0.0) {
                        ra2.getColList().add(s, rb.getColNum());
                    }
                    rb = rb.getNext();
                }
                ra = ra.getNext();
                ra2 = ra2.getNext();
            }
            return matrix;
        }
    }
//

    public static double mul(RowNode a, ColNode2 b) {
        ColList a1 = a.getColList();
        RowList2 b1 = b.getRowList2();
        if (a1.isEmpty() || b1.isEmpty()) {
            return 0.0;
        } else {
            ColNode as = a1.First();
            RowNode2 bs = b1.First();
            double result = 0.0;
            while (as != null && bs != null) {
                if (as.getColNumber() == bs.getRowNumber()) {
                    result = result + as.getValue() * bs.getValue();
                    as = as.getNext();
                    bs = bs.getNext();
                } else if (as.getColNumber() < bs.getRowNumber()) {
                    as = as.getNext();
                } else {
                    bs = bs.getNext();
                }
            }
            return Double.parseDouble(df.format(result));
        }
    }

    private static MyMatrix createEmptyRowMatrix(int m, int n) {
        RowList list = new RowList(null, 0);
        for (int i = 0; i < m; i++) {
            list.add(new ColList(null, 0), i);
        }
        return new MyMatrix(list, null, m, n);
    }
}

class MyMatrix {
    private RowList rowList;
    private ColList2 colList;
    private int m;
    private int n;

    public MyMatrix(RowList rowList, ColList2 colList, int m, int n) {
        this.rowList = rowList;
        this.colList = colList;
        this.m = m;
        this.n = n;
    }

    public RowList getRowList() {
        return rowList;
    }

    public void setRowList(RowList rowList) {
        this.rowList = rowList;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ColList2 getColList() {
        return colList;
    }

    public void setColList(ColList2 colList) {
        this.colList = colList;
    }


}

class ColList {
    private ColNode first;
    private int size;

    public ColList(ColNode first, int size) {
        this.first = first;
        this.size = size;
    }

    public int Size() {
        return size;
    }

    public boolean isEmpty() {
        return Size() == 0;
    }

    public ColNode First() {
        if (isEmpty()) {
            return null;
        }
        return first;
    }

    public ColNode Last() {
        if (isEmpty()) {
            return null;
        } else {
            ColNode n = First();
            while (n.getNext() != null) {
                n = n.getNext();
            }
            return n;
        }
    }


    public void add(double x, int colNum) {
        ColNode node = new ColNode(colNum, x, null);
        if (isEmpty()) {
            first = node;
        } else {
            ColNode last = Last();
            last.setNext(node);
        }
        size++;
    }

    public void addOrUpdate(double x, int colnum) {
        if (isEmpty()) {
            first = new ColNode(colnum, x, null);
            size++;
        } else {
            ColNode s = first;
            ColNode t = null;
            while (s != null) {
                if (s.getColNumber() == colnum) {
                    s.setValue(s.getValue() + x);
                    return;
                }
                if (s.getNext() == null) {
                    t = s;
                }
                s = s.getNext();
            }
            t.setNext(new ColNode(colnum, x, null));
            size++;
        }
    }

    public ColList clone(ColList colList) {
        ColList list = new ColList(null, 0);
        ColNode n = colList.First();
        while (n != null) {
            list.add(n.getValue(), n.getColNumber());
            n = n.getNext();
        }
        return list;
    }

    @Override
    public String toString() {
        return "Rows.ColList{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }
}

class ColNode {
    private int colNumber;
    private double value;
    private ColNode next;

    public ColNode(int colNumber, double value, ColNode next) {
        this.colNumber = colNumber;
        this.value = value;
        this.next = next;
    }


    public int getColNumber() {
        return colNumber;
    }

    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ColNode getNext() {
        return next;
    }

    public void setNext(ColNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Rows.ColNode{" +
                "colNumber=" + colNumber +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}

class RowList {
    private RowNode first;
    private int size;


    public RowList(RowNode first, int size) {
        this.first = first;
        this.size = size;
    }

    public int Size() {
        return size;
    }

    private boolean isEmpty() {
        return Size() == 0;
    }

    public RowNode First() {
        if (isEmpty()) {
            return null;
        }
        return first;
    }


    private RowNode Last() {
        if (isEmpty()) {
            return null;
        } else {
            RowNode n = First();
            while (n.getNext() != null) {
                n = n.getNext();
            }
            return n;
        }
    }

    public void add(ColList x, int rowNum) {
        RowNode node = new RowNode(rowNum, x, null);
        if (isEmpty()) {
            first = node;
        } else {
            RowNode last = Last();
            last.setNext(node);
        }
        size++;
    }

    public RowList(RowNode first) {
        this.first = first;
    }

    public RowList clone(RowList rowList) {
        RowList list = new RowList(null, 0);
        RowNode n = rowList.First();
        while (n != null) {
            list.add(n.getColList().clone(n.getColList()), n.getRowNum());
            n = n.getNext();
        }
        return list;
    }

    @Override
    public String toString() {
        return "Rows.RowList{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }
}

class RowNode {
    private int rowNum;
    private ColList colList;
    private RowNode next;

//    public Rows.RowNode() {
//    }

    public RowNode(int rowNum, ColList colList, RowNode next) {
        this.rowNum = rowNum;
        this.colList = colList;
        this.next = next;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public ColList getColList() {
        return colList;
    }

    public void setColList(ColList colList) {
        this.colList = colList;
    }

    public RowNode getNext() {
        return next;
    }

    public void setNext(RowNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Rows.RowNode{" +
                "rowNum=" + rowNum +
                ", colList=" + colList +
                ", next=" + next +
                '}';
    }

//    public Rows.RowNode clone(Rows.RowNode rowNode){
//        Rows.RowNode node = new Rows.RowNode(rowNode.getRowNum() , rowNode.getColList().clone(colList) , );
//    }
}

