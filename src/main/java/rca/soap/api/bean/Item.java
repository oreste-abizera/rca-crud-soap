package rca.soap.api.bean;

import rca.soap.api.bean.enums.Status;

import javax.persistence.*;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String itemCode;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Integer price;

	private Integer supplier;


	public Item() {
	}

	public Item(int id, String name, String itemCode, Status status, Integer price, Integer supplier) {
		this.id = id;
		this.name = name;
		this.itemCode = itemCode;
		this.status = status;
		this.price = price;
		this.supplier = supplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSupplier() {
		return supplier;
	}

	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", name='" + name + '\'' +
				", itemCode='" + itemCode + '\'' +
				", status=" + status +
				", price=" + price +
				", supplier=" + supplier +
				'}';
	}
}
