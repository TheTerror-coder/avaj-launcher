# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: jm <jm@student.42lyon.fr>                  +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2024/04/11 20:26:23 by TheTerror         #+#    #+#              #
#    Updated: 2025/02/14 10:58:55 by jm               ###   ########lyon.fr    #
#                                                                              #
# **************************************************************************** #

# Color codes for stylish output
RESET   = \033[0m
BOLD    = \033[1m
RED     = \033[31m
GREEN   = \033[32m
YELLOW  = \033[33m
BLUE    = \033[34m
CYAN    = \033[36m
WHITE   = \033[97m


NAME			= avaj_launcher
JC				= javac
JRUN			= java
JFLAGS			= 
ARGS 			?=

BIN_DIR			= .bin

MAIN_PACKAGE	= avajlauncher.main
MAIN_CLASS		= $(MAIN_PACKAGE).Main

SRC				= $(shell find * -name "*.java")
CLASS			= $(addprefix $(BIN_DIR)/, $(SRC:.java=.class))

RM				= rm -rf

RUN_CMD			= $(JRUN) -cp $(BIN_DIR) $(MAIN_CLASS)

# Rule to compile .java files to .class files
$(BIN_DIR)/%.class : %.java
	@echo "$(CYAN)Compiling $< to $@$(RESET)"
	@$(JC) $(JFLAGS) $< -d $(BIN_DIR)

### Targets ###
all : build

build : $(CLASS) log_run_cmd

run : build
	@echo "$(GREEN)Running the program $(MAIN_CLASS) with arguments '$(ARGS)'$(RESET)"
	@$(RUN_CMD) $(ARGS)

$(BIN_DIR):
	@mkdir -p $(BIN_DIR)


clean:
	@echo "$(RED)Cleaning up class files...$(RESET)"
	$(RM) $(BIN_DIR)

fclean: clean
	@echo "$(RED)Fully cleaned.$(RESET)"

re: log_re fclean all

log_re:
	@echo "$(BLUE)Rebuilding everything...$(RESET)"

log_run_cmd:
	@echo "$(WHITE)run command -->$(YELLOW) $(RUN_CMD)$(RESET)"

.PHONY: all clean fclean re log_re build run log_run_cmd
