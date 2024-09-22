package dto;

public record CardDTO(
    String number,
    String cvv,
    int yearExpiration,
    int monthExpiration
) {
}
