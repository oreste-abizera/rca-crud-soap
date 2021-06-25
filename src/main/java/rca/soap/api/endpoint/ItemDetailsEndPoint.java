package rca.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rca.soap.api.bean.Item;
import rca.soap.api.bean.enums.Status;
import rca.soap.api.repository.IItemRepository;
import rca.soap.oreste.items.*;

import java.util.List;
import java.util.Optional;

@Endpoint
public class ItemDetailsEndPoint {

	@Autowired
	private IItemRepository itemRepository;

	// method
	// request ---- GetItemDetailsRequest
	// response --- GetItemDetailsResponse
	@PayloadRoot(namespace = "http://soap.rca/oreste/items", localPart = "GetItemDetailsRequest")
	@ResponsePayload
	public GetItemDetailsResponse getById(@RequestPayload GetItemDetailsRequest request) {

		Item item = itemRepository.findById(request.getId()).get();

		GetItemDetailsResponse itemDetailsResponse = mapItemDetails(item);
		return itemDetailsResponse;
	}

	@PayloadRoot(namespace = "http://soap.rca/oreste/items", localPart = "GetAllItemDetailsRequest")
	@ResponsePayload
	public GetAllItemDetailsResponse getAll(@RequestPayload GetAllItemDetailsRequest request) {

		GetAllItemDetailsResponse allItemDetailsResponse = new GetAllItemDetailsResponse();
		List<Item> items = itemRepository.findAll();
		for (Item item : items) {
			GetItemDetailsResponse itemDetailsResponse = mapItemDetails(item);
			allItemDetailsResponse.getItemDetails().add(itemDetailsResponse.getItemDetails());
		}

		return allItemDetailsResponse;
	}
	@PayloadRoot(namespace = "http://soap.rca/oreste/items", localPart = "CreateItemDetailsRequest")
	@ResponsePayload
	public CreateItemDetailsResponse save(@RequestPayload CreateItemDetailsRequest request) {
		itemRepository.save(new Item(request.getItemDetails().getId(),
				request.getItemDetails().getName(),
				request.getItemDetails().getItemCode(),
				Status.valueOf(request.getItemDetails().getStatus()),
				request.getItemDetails().getPrice(),
				request.getItemDetails().getSupplier()
				));
		CreateItemDetailsResponse itemDetailsResponse = new CreateItemDetailsResponse();
		itemDetailsResponse.setItemDetails(request.getItemDetails());
		itemDetailsResponse.setMessage("Created Successfully");
		return itemDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soap.rca/oreste/items", localPart = "UpdateItemDetailsRequest")
	@ResponsePayload
	public UpdateItemDetailsResponse update(@RequestPayload UpdateItemDetailsRequest request) {
		UpdateItemDetailsResponse itemDetailsResponse = null;
		Optional<Item> existingItem = this.itemRepository.findById(request.getItemDetails().getId());
		if(existingItem.isEmpty() || existingItem == null) {
			itemDetailsResponse = mapItemDetail(null, "Id not found");
		}
		if(existingItem.isPresent()) {
			
			Item _item = existingItem.get();
			_item.setName(request.getItemDetails().getName());
			_item.setItemCode(request.getItemDetails().getItemCode());
			_item.setStatus(Status.valueOf(request.getItemDetails().getStatus()));
			_item.setPrice(request.getItemDetails().getPrice());
			_item.setSupplier(request.getItemDetails().getSupplier());
			itemRepository.save(_item);
			itemDetailsResponse = mapItemDetail(_item, "Updated successfully");
			
		}
		return itemDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soap.rca/oreste/items", localPart = "DeleteItemDetailsRequest")
	@ResponsePayload
	public DeleteItemDetailsResponse delete(@RequestPayload DeleteItemDetailsRequest request) {
		
		itemRepository.deleteById(request.getId());
		
		DeleteItemDetailsResponse itemDetailsResponse = new DeleteItemDetailsResponse();
		itemDetailsResponse.setMessage("Deleted Successfully");
		return itemDetailsResponse;
	}

	private GetItemDetailsResponse mapItemDetails(Item item) {
		ItemDetails itemDetails = mapItem(item);

		GetItemDetailsResponse itemDetailsResponse = new GetItemDetailsResponse();

		itemDetailsResponse.setItemDetails(itemDetails);
		return itemDetailsResponse;
	}
	private UpdateItemDetailsResponse mapItemDetail(Item item, String message) {
		ItemDetails itemDetails = mapItem(item);
		UpdateItemDetailsResponse itemDetailsResponse = new UpdateItemDetailsResponse();

		itemDetailsResponse.setItemDetails(itemDetails);
		itemDetailsResponse.setMessage(message);
		return itemDetailsResponse;
	}

	private ItemDetails mapItem(Item item) {
		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setName(item.getName());
		itemDetails.setItemCode(item.getItemCode());
		itemDetails.setPrice(item.getPrice());
		itemDetails.setStatus(item.getStatus().toString());
		itemDetails.setSupplier(item.getSupplier());
		return itemDetails;
	}

}
