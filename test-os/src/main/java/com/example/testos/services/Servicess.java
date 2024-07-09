package com.example.testos.services;

import com.example.testos.dto.ClientOrderDTO;
import com.example.testos.dto.OrderofServiceDto;
import com.example.testos.entity.Client;
import com.example.testos.entity.OrderofService;
import com.example.testos.repository.ClientRepository;
import com.example.testos.repository.GuaranteeRepository;
import com.example.testos.repository.OrderofServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Servicess {

    private final OrderofServiceRepository orderRepository;
    private final ClientRepository clientRepository;
    private final GuaranteeRepository guaranteeRepository;

    @Autowired
    public Servicess(OrderofServiceRepository orderRepository, ClientRepository clientRepository, GuaranteeRepository guaranteeRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.guaranteeRepository = guaranteeRepository;
    }

    public void deleteByid(Long id){
        orderRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        clientRepository.deleteByName(name);
    }

    public void deleteGuaranteeById(Long id){
        guaranteeRepository.deleteById(id);
    }

//    public List<ClientOrderDTO> getClientsWithOrderofServices() {
//        List<ClientOrderDTO> result = orderRepository.findClientsWithOrderofServices();
//        if (result.isEmpty()) {
//            System.out.println("Nenhum dado encontrado no serviço");
//        } else {
//            System.out.println("Dados encontrados no serviço: " + result.size());
//        }
//        return result;
//    }


    public List<ClientOrderDTO> getClientsWithOrderofServices() {
        List<Client> clients = clientRepository.findAll();
        List<OrderofService> orderofServices = orderRepository.findAll();

        return clients.stream().map(client -> {
            List<OrderofServiceDto> orderofServicesDTO = orderofServices.stream()
                    .filter(order -> order.getClient().getId().equals(client.getId()))
                    .map(order -> new OrderofServiceDto(
                            order.getId(),
                            order.getDevice(),
                            order.getClaims(),
                            order.getObservation(),
                            order.getValue(),
                            order.getDate()
                    )).collect(Collectors.toList());

            return new ClientOrderDTO(
                    client.getId(),
                    client.getName(),
                    client.getTelephone(),
                    client.getAddres(),
                    client.getNeighboard(),
                    orderofServicesDTO
            );
        }).collect(Collectors.toList());
    }
}
