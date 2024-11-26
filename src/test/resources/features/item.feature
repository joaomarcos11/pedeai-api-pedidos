# Feature: Item management
#   Scenario: Get Item by id
#     Given an item exists with ID "257ae14b-8bb7-4a80-9a68-22197f72ff47"
#     When I send a GET request to "/itens/257ae14b-8bb7-4a80-9a68-22197f72ff47":
#     Then the response code should be 200
#     And the response should contain:
#       """
#       {
#         "id": "257ae14b-8bb7-4a80-9a68-22197f72ff47",
#         "nome": "SANDUICHE",
#         "preco": 550,
#         "categoria": "LANCHE",
#         "descricao": "um delicioso sanduiche",
#         "imagem": "/var/app/imagens/sanduiche.jpg"
#       }
#       """