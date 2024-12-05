public class PageOrderRule{
  private final int before;
  private final int after;

  public PageOrderRule(int b, int a){
    this.before = b;
    this.after = a;
  }

  public int before(){
    return this.before;
  }

  public int after(){
    return this.after;
  }
  
  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof PageOrderRule)){ return false; }
    PageOrderRule other = (PageOrderRule)o;
    return (this.before() == other.before() && this.after() == other.after());
  }

  public String toString(){
    return "(" + this.before() + "|" + this.after() + ")";
  }
}
