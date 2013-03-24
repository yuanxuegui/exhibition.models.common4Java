package cn.ac.sec.exhibition.domain;

public interface IItemFinder<T, K> {
	T getItemByKey(K key);
}
