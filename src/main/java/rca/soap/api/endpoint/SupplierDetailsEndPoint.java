package rca.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rca.soap.api.bean.Supplier;
import rca.soap.api.repository.ISupplierRepository;
import rca.soap.oreste.suppliers.*;

import java.util.List;
import java.util.Optional;

@Endpoint
public class SupplierDetailsEndPoint {

	@Autowired
	private ISupplierRepository supplierRepository;

	// method
	// request ---- GetSupplierDetailsRequest
	// response --- GetSupplierDetailsResponse
	@PayloadRoot(namespace = "http://soap.rca/oreste/suppliers", localPart = "GetSupplierDetailsRequest")
	@ResponsePayload
	public GetSupplierDetailsResponse findById(@RequestPayload GetSupplierDetailsRequest request) {

		Supplier supplier = supplierRepository.findById(request.getId()).get();

		GetSupplierDetailsResponse supplierDetailsResponse = mapSupplierDetails(supplier);
		return supplierDetailsResponse;
	}

	@PayloadRoot(namespace = "http://soap.rca/oreste/suppliers", localPart = "GetAllSupplierDetailsRequest")
	@ResponsePayload
	public GetAllSupplierDetailsResponse findAll(@RequestPayload GetAllSupplierDetailsRequest request) {

		GetAllSupplierDetailsResponse allSupplierDetailsResponse = new GetAllSupplierDetailsResponse();
		List<Supplier> suppliers = supplierRepository.findAll();
		for (Supplier supplier : suppliers) {
			GetSupplierDetailsResponse supplierDetailsResponse = mapSupplierDetails(supplier);
			allSupplierDetailsResponse.getSupplierDetails().add(supplierDetailsResponse.getSupplierDetails());
		}

		return allSupplierDetailsResponse;
	}
	@PayloadRoot(namespace = "http://soap.rca/oreste/suppliers", localPart = "CreateSupplierDetailsRequest")
	@ResponsePayload
	public CreateSupplierDetailsResponse save(@RequestPayload CreateSupplierDetailsRequest request) {
		supplierRepository.save(new Supplier(request.getSupplierDetails().getId(),
				request.getSupplierDetails().getNames(),
				request.getSupplierDetails().getEmail(),
				request.getSupplierDetails().getMobile()
				));
		CreateSupplierDetailsResponse supplierDetailsResponse = new CreateSupplierDetailsResponse();
		supplierDetailsResponse.setSupplierDetails(request.getSupplierDetails());
		supplierDetailsResponse.setMessage("Created Successfully");
		return supplierDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soap.rca/oreste/suppliers", localPart = "UpdateSupplierDetailsRequest")
	@ResponsePayload
	public UpdateSupplierDetailsResponse update(@RequestPayload UpdateSupplierDetailsRequest request) {
		UpdateSupplierDetailsResponse supplierDetailsResponse = null;
		Optional<Supplier> existingSupplier = this.supplierRepository.findById(request.getSupplierDetails().getId());
		if(existingSupplier.isEmpty() || existingSupplier == null) {
			supplierDetailsResponse = mapSupplierDetail(null, "Id not found");
		}
		if(existingSupplier.isPresent()) {
			
			Supplier _supplier = existingSupplier.get();
			_supplier.setNames(request.getSupplierDetails().getNames());
			_supplier.setEmail(request.getSupplierDetails().getEmail());
			_supplier.setMobile(request.getSupplierDetails().getMobile());
			supplierRepository.save(_supplier);
			supplierDetailsResponse = mapSupplierDetail(_supplier, "Updated successfully");
			
		}
		return supplierDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soap.rca/oreste/suppliers", localPart = "DeleteSupplierDetailsRequest")
	@ResponsePayload
	public DeleteSupplierDetailsResponse save(@RequestPayload DeleteSupplierDetailsRequest request) {
		
		supplierRepository.deleteById(request.getId());
		
		DeleteSupplierDetailsResponse supplierDetailsResponse = new DeleteSupplierDetailsResponse();
		supplierDetailsResponse.setMessage("Deleted Successfully");
		return supplierDetailsResponse;
	}

	private GetSupplierDetailsResponse mapSupplierDetails(Supplier supplier) {
		SupplierDetails supplierDetails = mapSupplier(supplier);

		GetSupplierDetailsResponse supplierDetailsResponse = new GetSupplierDetailsResponse();

		supplierDetailsResponse.setSupplierDetails(supplierDetails);
		return supplierDetailsResponse;
	}
	private UpdateSupplierDetailsResponse mapSupplierDetail(Supplier supplier, String message) {
		SupplierDetails supplierDetails = mapSupplier(supplier);
		UpdateSupplierDetailsResponse supplierDetailsResponse = new UpdateSupplierDetailsResponse();

		supplierDetailsResponse.setSupplierDetails(supplierDetails);
		supplierDetailsResponse.setMessage(message);
		return supplierDetailsResponse;
	}

	private SupplierDetails mapSupplier(Supplier supplier) {
		SupplierDetails supplierDetails = new SupplierDetails();
		supplierDetails.setNames(supplier.getNames());
		supplierDetails.setEmail(supplier.getEmail());
		supplierDetails.setMobile(supplier.getMobile());
		return supplierDetails;
	}

}
