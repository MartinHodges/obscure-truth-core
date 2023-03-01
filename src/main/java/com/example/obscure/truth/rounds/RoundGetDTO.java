package com.example.obscure.truth.rounds;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.obscure.truth.facts.FactGetDTO;
import com.example.obscure.truth.player.PlayerGetDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Provides information about a round")

public class RoundGetDTO {

	@Schema(description = "Unique Id of this round", example = "D674-...-HTR3")
	private String uuid;

	@Schema(description = "The id of the suspect", example = "1234-...-AC79")
	private PlayerGetDTO suspect;

	@Schema(description = "The number of detectives in this round", example = "1234-...-AC79")
	private int numberDetectives;

	@Schema(description = "The current number of deductions", example = "1234-...-AC79")
	private int numberDeductions;
	
	@Schema(description = "The list of facts provided by the suspect", example = "[...]")
	private Set<FactGetDTO> facts;
	
	public RoundGetDTO(RoundEntity round) {
		uuid = round.getId().toString();
		suspect  = new PlayerGetDTO(round.getGame(),round.getSuspect());
		facts = round.getFacts().stream()
				.map(fact -> new FactGetDTO(fact))
				.collect(Collectors.toSet());
		numberDetectives = round.getGame().getPlayers().size() - 1;
		numberDeductions = round.getDeductions().size() / 3;
	}
}
