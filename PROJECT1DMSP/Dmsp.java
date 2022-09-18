import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;            
import org.omg.CORBA.INTERNAL;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.math.*;
import java.rmi.Remote;
import java.sql.Array;
public class MyClass {

public static void main(String[] args)throws java.lang.Exception{

ArrayList<sort_class> sc=new ArrayList<>(); // arraylist to store edges , vertices so that edges can be sorted
sc.add(new sort_class(1, 2, 2));
sc.add(new sort_class(1, 3, 8));
sc.add(new sort_class(1,  4, 14));
sc.add(new sort_class(3, 4, 21));
sc.add(new sort_class(2, 4, 25));
sc.add(new sort_class(2, 5, 19));
sc.add(new sort_class(4, 5, 17));
sc.add(new sort_class(4, 6, 13));
sc.add(new sort_class(5, 6, 5));
sc.add(new sort_class(5, 7, 9));
sc.add(new sort_class(6, 7, 1));

Collections.sort(sc); // sorting edges according to their weight

int[] a={1, 2, 3, 4, 5, 6, 7}; // vertices
graph g=new graph(7, 21, a, sc);
g.makeset(); // function to make each vertex a node of itself for disjoint set
g.kruskal();
}
}
class graph{
int ver, edge;
result head=null;
ArrayList<sort_class> s=new ArrayList<>();
Map<Integer, node> map=new HashMap<Integer, node>(); // to store vertex and node of the vertex
int[] vertices; // for vertices
public graph(int ver, int edge,int[] vertice, ArrayList<sort_class> sc){
this.ver=ver;
this.edge=edge;
this.vertices=new int[this.ver];
this.vertices=vertice;

this.s=sc;
}
public void kruskal(){
int x, y,z;
for(sort_class set:s){ // calling edges from lowest weight to highest weight
x=set.u; // first vertex
y=set.v; 
z=set.w;// second vertex
node n1=findSet(map.get(x));
node n2=findSet(map.get(y));
//node n3=findSet(map.get(z));

if(n1.parent.data!=n2.parent.data){  // checks if both vertex belongs to the same set
result res=new result(x, y,z);
if(head==null){
res.next=null;
head=res;
}else{
result r=head;
while(r.next!=null){
r=r.next;
}
r.next=res;
res.next=null;
}
union(x, y);
}
}
result r=head; 
int tot=0;
while(r!=null){  // start printing result edges | minimum spanning tree
System.out.println(r.u+"->"+r.v);
tot+=r.w;
r=r.next;

}
System.out.println("Minimum Cost -> "+tot);
}
public void makeset(){
for(int i=0;i<this.ver;i++){
node nd=new node();
nd.data=vertices[i];
nd.rank=0;
nd.parent=nd;
map.put(nd.data, nd);	
}

}
public void union(int u, int v){
node n1=map.get(u);
node n2=map.get(v);

node parent1=findSet(n1);
node parent2=findSet(n2);
if(parent1.data>=parent2.data){
parent1.rank=(parent1.rank==parent2.rank)?parent1.rank+1:parent1.rank;
parent2.parent=parent1;
}else{
parent1.parent=parent2;
}

}
public node findSet(node nd){
node p=nd.parent;
if(p==nd){
return p;
}
nd.parent=findSet(nd.parent);
return nd.parent;
}
public int getSet(int x){
return findSet(map.get(x)).data;
}
}
class node{
int data, rank;
node parent;
}
class result{
int u, v,w;
result next;
public result(int u, int v,int w){
this.u=u;
this.v=v;
this.w=w;
this.next=null;
}
}
class sort_class implements Comparable<sort_class>{    //calling comparable interface to sort sort_class according to their edges
int u, v, w;
public sort_class(int u, int v, int w){
this.u=u;
this.v=v;
this.w=w;
}
@Override
public int compareTo(sort_class o) {
return this.w-o.w;
}
}