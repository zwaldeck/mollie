package be.woutschoovaerts.mollie.data.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineOperationRequest<T extends OrderLineOperationDataRequest> {

    private OrderLineOperationType operation;

    private T data;

    public static OrderLineOperationRequest<OrderLineRequest> addOperation(OrderLineRequest request) {
        return OrderLineOperationRequest.<OrderLineRequest>builder()
                .operation(OrderLineOperationType.ADD)
                .data(request)
                .build();
    }

    public static OrderLineOperationRequest<UpdateOrderLineRequest> updateOperation(UpdateOrderLineRequest request) {
        return OrderLineOperationRequest.<UpdateOrderLineRequest>builder()
                .operation(OrderLineOperationType.UPDATE)
                .data(request)
                .build();
    }

    public static OrderLineOperationRequest<CancelOrderLineRequest> cancelOperation(CancelOrderLineRequest request) {
        return OrderLineOperationRequest.<CancelOrderLineRequest>builder()
                .operation(OrderLineOperationType.CANCEL)
                .data(request)
                .build();
    }
}
