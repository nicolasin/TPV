package es.nico.wata.tpv.interfaces;
import java.util.*;
import es.nico.wata.tpv.exceptions.*;
public interface ControlInterface<T, I> {
	public void insert(T t)throws ControlException;
	public T getOne(I i)throws ControlException;
	public List<T> getAll()throws ControlException;
	public void remove(I i)throws ControlException;
	public void modify(T t)throws ControlException;
}
