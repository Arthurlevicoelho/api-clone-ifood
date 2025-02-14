//package com.rm.ifood_backend;
//
//import com.rm.ifood_backend.model.client.Client;
//import com.rm.ifood_backend.model.client.UpdateClientDTO;
//import com.rm.ifood_backend.repository.ClientRepository;
//import com.rm.ifood_backend.service.ClientService;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ClientServiceTest {
//
//  @InjectMocks
//  private ClientService clientService;
//
//  @Mock
//  private ClientRepository clientRepository;
//
//  private UUID clientId;
//
//  private Client client;
//
//  private UpdateClientDTO clientUpdater;
//
//  @BeforeEach
//  void setUp() {
//    clientId = UUID.randomUUID();
//
//    client = Client.builder()
//        .id(clientId)
//        .name("TestUser")
//        .email("teste@gmail.com")
//        .password("teste123@")
//        .cpf("12345678910")
//        .address("rua xyz")
//        .phone("99123456789")
//        .build();
//
//    clientUpdater = UpdateClientDTO.builder()
//        .id(clientId)
//        .name("TestUser Updated")
//        .email("teste2@gmail.com")
//        .password("teste1234@")
//        .cpf("12345678910")
//        .address("rua tal xyz")
//        .phone("99123456789")
//        .build();
//  }
//
//  @Test
//  void shouldReturnAllClients() {
//    when(clientRepository.findAll()).thenReturn(List.of(client));
//
//    List<Client> clients = clientService.getAll();
//
//    assertFalse(clients.isEmpty());
//    assertEquals(1, clients.size());
//
//    verify(clientRepository, times(1)).findAll();
//  }
//
//  @Test
//  void shouldFindClientById() {
//    when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
//
//    Client foundClient = clientRepository.findById(clientId).orElse(null);
//
//    assertNotNull(foundClient);
//    assertEquals(clientId, foundClient.getId());
//
//    verify(clientRepository, times(1)).findById(clientId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenClientNotFound() {
//    when(clientRepository.existsById(clientId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> clientService.findById(clientId));
//
//    verify(clientRepository, times(1)).existsById(clientId);
//  }
//
//  @Test
//  void shouldCreateClient() {
//    when(clientRepository.save(client)).thenReturn(client);
//
//    Client savedClient = clientService.create(client);
//
//    assertNotNull(savedClient);
//    assertEquals(clientId, savedClient.getId());
//
//    verify(clientRepository, times(1)).save(client);
//  }
//
//  @Test
//  void shouldUpdateClient() {
//    when(clientRepository.existsById(clientId)).thenReturn(true);
//    when(clientRepository.save(clientUpdater)).thenReturn(clientUpdater);
//
//    Client updatedClient = clientService.update(clientId, clientUpdater);
//
//    assertNotNull(updatedClient);
//    assertEquals(clientId, updatedClient.getId());
//
//    verify(clientRepository, times(1)).existsById(clientId);
//    verify(clientRepository, times(1)).save(clientUpdater);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenUpdatingNonExistentClient() {
//    when(clientRepository.existsById(clientId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> clientService.update(clientId, clientUpdater));
//
//    verify(clientRepository, times(1)).existsById(clientId);
//    verify(clientRepository, never()).findById(any(UUID.class));
//    verify(clientRepository, never()).save(any(Client.class));
//  }
//
//  @Test
//  void shouldDeleteClient() {
//    when(clientRepository.existsById(clientId)).thenReturn(true);
//
//    doNothing().when(clientRepository).deleteById(clientId);
//
//    assertDoesNotThrow(() -> clientService.delete(clientId));
//
//    verify(clientRepository, times(1)).existsById(clientId);
//    verify(clientRepository, times(1)).deleteById(clientId);
//  }
//
//  @Test
//  void shouldThrowExceptionWhenDeletingNonExistentClient() {
//    when(clientRepository.existsById(clientId)).thenReturn(false);
//
//    assertThrows(EntityNotFoundException.class, () -> clientService.delete(clientId));
//
//    verify(clientRepository, times(1)).existsById(clientId);
//    verify(clientRepository, never()).deleteById(any(UUID.class));
//  }
//}
